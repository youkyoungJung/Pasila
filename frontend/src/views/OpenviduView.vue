<script setup>
import { onMounted, onUnmounted, ref, reactive, watch } from 'vue'
import router from '@/router'
import { OpenVidu } from 'openvidu-browser'
import Stomp from 'webstomp-client'
import SockJs from 'sockjs-client'
import {
  createSessionApi,
  createTokenApi,
  getLiveProductApi,
  startLiveApi,
  stopLiveApi,
  getLiveQuestionApi,
  sendChatToChatbot
} from '@/components/api/OpenviduAPI.js'

import { getLiveStockApi } from '@/components/api/RealTimeAPI'
import { useLiveendStore } from '@/stores/liveend'
import UserVideo from '@/components/live/openvidu/UserVideo.vue'
import LiveScript from '@/components/live/seller/LiveScript.vue'
import LiveStock from '@/components/live/seller/LiveStock.vue'
import LiveQuestion from '@/components/live/seller/LiveQuestion.vue'
import LiveChat from '@/components/live/LiveChat.vue'
import LiveDescription from '@/components/live/customer/LiveDescription.vue'
import ToolBarBtn from '@/components/live/ToolBarBtn.vue'
import { likeUpApi } from '@/components/api/RedisAPI'

let OV = ref(undefined)
let session = ref(undefined)
let mainStreamManager = ref(undefined)
let publisher = ref(undefined)
let subscribers = ref([])

let ws
let chatmsg = ref('')
let isChatbot = ref(false)
const chatList = ref([])
const customerCnt = ref(0)

let userRole = ref('')
let isStart = ref(false)

let product = reactive({})
let questionList = ref([])

const props = defineProps(['liveId'])
const liveendStore = useLiveendStore()

let interval

onMounted(async () => {
  await getProduct()
  const memberId = localStorage.getItem('id')
  if (!memberId) {
    alert('로그인 후 시청 가능합니다.')
    router.push('/login')
  } else if (product.sellerId == memberId) {
    userRole.value = 'PUB'
  } else {
    userRole.value = 'SUB'
  }

  if (userRole.value === 'PUB') {
    const stockEvent = getLiveStockApi(props.liveId)

    stockEvent.addEventListener('sse', (e) => {
      const data = JSON.parse(e.data)
      if (data.liveId) {
        product.options = data.options
      }
    })
    interval = setInterval(async () => {
      questionList.value = await getLiveQuestionApi(props.liveId)
    }, 60000)
  }

  connectChat()
  joinSession()

  if (ws && ws.connected) {
    const msg = {
      liveId: props.liveId
    }
    ws.send(`/send/join`, JSON.stringify(msg), {})
  }
})

onUnmounted(() => {
  clearInterval(interval)
  if (ws && ws.connected) {
    const msg = {
      liveId: props.liveId
    }
    ws.send(`/send/exit`, JSON.stringify(msg), {})
  }
  ws.disconnect()
  leaveSession()
})

const getProduct = async () => {
  product = await getLiveProductApi(props.liveId)
}

/**
 * tool bar 클릭 시 상태를 toggle 해주는 함수입니다.
 */
const clickToolBarBtn = (arr, n) => {
  arr[n].isActive = !arr[n].isActive
}

const joinSession = async () => {
  OV.value = new OpenVidu()
  OV.value.enableProdMode()
  session.value = OV.value.initSession()

  session.value.on('streamCreated', ({ stream }) => {
    const subscriber = session.value.subscribe(stream, undefined)
    subscribers.value.push(subscriber)
  })

  session.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.indexOf(stream.streamManager, 0)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })

  session.value.on('exception', () => {
    alert('진행중인 라이브가 아닙니다.')
    router.push('/')
  })

  let token
  if (userRole.value === 'PUB') {
    const res = await getToken(props.liveId)
    token = res
  } else {
    const res = await createTokenApi(props.liveId)
    token = res
  }
  // const token = await getToken(props.liveId)

  try {
    await session.value.connect(token)
    if (userRole.value === 'PUB') {
      const publisherInfo = OV.value.initPublisher(undefined, {
        audioSource: undefined,
        videoSource: undefined,
        publishAudio: true,
        publishVideo: true,
        frameRate: 30,
        insertMode: 'APPEND',
        mirror: false
      })

      mainStreamManager.value = publisherInfo
      publisher.value = publisherInfo
      session.value.publish(publisher.value)
    }
  } catch (error) {
    alert('진행중인 라이브가 아닙니다.')
    router.push('/')
  }

  window.addEventListener('beforeunload', leaveSession)
}

const startLive = async () => {
  if (await startLiveApi(props.liveId)) {
    isStart.value = true
  }
}

const stopLive = async () => {
  const res = await stopLiveApi(props.liveId)
  console.log(res)
  liveendStore.liveendInfo = res
}

const leaveSession = async () => {
  if (session.value) {
    if (userRole.value == 'PUB' && isStart.value && confirm('라이브를 정말 종료하시겠습니까?')) {
      await stopLive()
      session.value.disconnect()
      router.push(`/live/${props.liveId}/end`)
      return
    } else {
      session.value.disconnect()
    }
  } else {
    session.value.disconnect()
  }

  session.value = undefined
  mainStreamManager.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OV.value = undefined

  window.removeEventListener('beforeunload', leaveSession)
}

const getToken = async (liveId) => {
  const res = await createSessionApi(liveId)
  return await createTokenApi(res)
}

const likeCntUp = async () => {
  await likeUpApi(props.liveId)
}

const pubToolBar = reactive([
  { isActive: true, iconName: 'fa-regular fa-file-lines', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-comments', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-rectangle-list', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-circle-question', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-circle-play', click: startLive }
])

const subToolBar = reactive([
  { isActive: true, iconName: 'fa-regular fa-heart', click: likeCntUp },
  { isActive: true, iconName: 'fa-regular fa-comments', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-rectangle-list', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-circle-xmark', click: leaveSession }
])

watch(
  () => isStart.value,
  () => {
    if (isStart.value) {
      pubToolBar.pop()
      pubToolBar.push({
        isActive: true,
        iconName: 'fa-regular fa-circle-xmark',
        click: leaveSession
      })
    }
  }
)

const clickChatbot = () => {
  isChatbot.value = !isChatbot.value
}

const sendChat = async () => {
  if (chatmsg.value.length <= 0) {
    alert('내용을 입력하세요')
    return
  }
  if (ws && ws.connected) {
    const msg = {
      liveId: props.liveId,
      message: chatmsg.value,
      name: localStorage.getItem('name'),
      profile: localStorage.getItem('profile')
    }
    ws.send(`/send/chatting`, JSON.stringify(msg), {})
  }
  const msg = chatmsg.value
  chatmsg.value = ''
  if (isChatbot.value) {
    const data = {
      liveId: props.liveId,
      message: msg
    }
    const res = await sendChatToChatbot(data)
    if (res) {
      chatList.value.push({
        name: 'PASILA봇',
        message: res
      })
    }
  }
}

const connectChat = () => {
  const serverURL = 'https://i10a402.p.ssafy.io/stomp/pasila'
  const socket = new SockJs(serverURL)
  ws = Stomp.over(socket, { debug: false })

  ws.connect(
    { Authorization: `Bearer ${localStorage.getItem('token')}` },
    () => {
      ws.subscribe(`/id/${props.liveId}`, (res) => {
        chatList.value.push(JSON.parse(res.body))
      })
      ws.subscribe(`/num/${props.liveId}`, (res) => {
        console.log(JSON.parse(res.body))
        customerCnt.value = JSON.parse(res.body)
      })
    },
    (error) => {
      console.log('소켓 연결 실패', error)
    }
  )
}
</script>

<template>
  <template v-if="userRole === 'PUB'">
    <div class="session" v-if="session">
      <section class="col-1">
        <user-video :stream-manager="mainStreamManager" :is-start="isStart" :cnt="customerCnt" />
        <live-script v-if="pubToolBar[0].isActive" :script="product.script" />
      </section>

      <section class="col-2" v-if="pubToolBar[1].isActive">
        <live-chat
          :is-customer="userRole === 'SUB'"
          :is-chatbot="isChatbot"
          :chatmsg="chatmsg"
          @change-msg="(e) => (chatmsg = e)"
          @send-msg="sendChat"
          @send="sendChat"
          @click-chatbot="clickChatbot"
          :chat-list="chatList"
        />
      </section>

      <section class="col-3" v-if="pubToolBar[2].isActive || pubToolBar[3].isActive">
        <live-stock v-if="pubToolBar[2].isActive" :product-options="product.options" />
        <live-question v-if="pubToolBar[3].isActive" :question-list="questionList" />
      </section>
    </div>
    <div class="tool-bar">
      <template v-for="(item, index) in pubToolBar" :key="index">
        <tool-bar-btn
          :is-active="item.isActive"
          :icon-name="item.iconName"
          @click-btn="item.click(pubToolBar, index)"
        />
      </template>
    </div>
  </template>
  <template v-else>
    <div class="session" v-if="session">
      <section class="col-1">
        <user-video :stream-manager="subscribers[0]" :is-start="true" :cnt="customerCnt" />
      </section>

      <section class="col-2" v-if="subToolBar[1].isActive">
        <live-chat
          :is-customer="userRole === 'SUB'"
          :is-chatbot="isChatbot"
          :chatmsg="chatmsg"
          @change-msg="(e) => (chatmsg = e)"
          @send-msg="sendChat"
          @send="sendChat"
          @click-chatbot="clickChatbot"
          :chat-list="chatList"
        />
      </section>

      <section class="col-3" v-if="subToolBar[2].isActive">
        <live-description :product="product" :live-id="props.liveId" />
      </section>
    </div>
    <div class="tool-bar">
      <template v-for="(item, index) in subToolBar" :key="index">
        <tool-bar-btn
          :is-active="item.isActive"
          :icon-name="item.iconName"
          @click-btn="item.click(subToolBar, index)"
        />
      </template>
    </div>
  </template>
</template>

<style lang="scss" scoped>
.session {
  @include flex-box(flex-start, center);
  height: 78vh;
  overflow-y: auto;
  padding: 0rem 2rem;
  .col-1 {
    flex: 2;
    padding: 0 1rem 0 0;

    display: flex;
    gap: 1rem;
    flex-direction: column;
    justify-content: flex-start;
  }
  .col-2 {
    flex: 1;
    padding: 0 1rem 0 0;

    display: flex;
    flex-direction: column;
  }
  .col-3 {
    flex: 1;
    padding: 0;

    display: flex;
    gap: 1rem;
    flex-direction: column;
    justify-content: space-between;
  }
}
.tool-bar {
  position: fixed;
  bottom: 0;
  @include box(100%, 10vh, white, 0, 0, 0);
  @include flex-box();
}
</style>
