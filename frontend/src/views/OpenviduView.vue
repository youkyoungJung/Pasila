<script setup>
import { onMounted, onUnmounted, ref, reactive, watch } from 'vue'
import router from '@/router'
import { OpenVidu } from 'openvidu-browser'
import {
  createSessionApi,
  createTokenApi,
  getLiveProductApi,
  startLiveApi,
  stopLiveApi,
  getLiveQuestionApi
} from '@/components/api/OpenviduAPI.js'
import { getLiveStockApi } from '@/components/api/RealTimeAPI'
import { useMemberStore } from '@/stores/member'
import UserVideo from '@/components/live/openvidu/UserVideo.vue'
import LiveScript from '@/components/live/seller/LiveScript.vue'
import LiveStock from '@/components/live/seller/LiveStock.vue'
import LiveQuestion from '@/components/live/seller/LiveQuestion.vue'
import LiveChat from '@/components/live/LiveChat.vue'
import LiveDescription from '@/components/live/customer/LiveDescription.vue'
import ToolBarBtn from '@/components/live/ToolBarBtn.vue'

let OV = ref(undefined)
let session = ref(undefined)
let mainStreamManager = ref(undefined)
let publisher = ref(undefined)
let subscribers = ref([])

let userRole = ref('')
let isStart = ref(false)

let product = reactive({})
let questionList = ref([])

const props = defineProps(['liveId'])
const { member } = useMemberStore()

onMounted(async () => {
  await getProduct()
  if (!member.id) {
    alert('로그인 후 시청 가능합니다.')
    router.push('/login')
  } else if (product.sellerId === member.id) {
    userRole = 'PUB'
  } else {
    userRole = 'SUB'
  }

  const stockEvent = getLiveStockApi(props.liveId)

  stockEvent.addEventListener('sse', (e) => {
    const data = JSON.parse(e.data)
    if (data.liveId) {
      product.options = data.options
    }
  })

  joinSession()

  setInterval(async () => {
    questionList.value = await getLiveQuestionApi(props.liveId)
  }, 300000)
})

onUnmounted(() => {
  leaveSession()
})

const getProduct = async () => {
  product = await getLiveProductApi(props.liveId)
}

/**
 * tool bar의 상태를 클릭 시 toggle 해주는 함수입니다.
 */
const clickToolBarBtn = (arr, n) => {
  arr[n].isActive = !arr[n].isActive
}

const joinSession = async () => {
  OV.value = new OpenVidu()
  OV.value.enableProdMode()
  session.value = OV.value.initSession()

  session.value.on('streamCreated', ({ stream }) => {
    const subscriber = session.value.subscribe(stream)
    subscribers.value.push(subscriber)
  })

  session.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.indexOf(stream.streamManager, 0)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })

  session.value.on('exception', ({ exception }) => {
    alert('서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해 주세요.')
    router.push('/')
  })

  const res = await getToken(props.liveId)
  const token = res

  try {
    await session.value.connect(token, { clientData: member.name })

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
  } catch (error) {
    alert('서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해 주세요.')
  }

  window.addEventListener('beforeunload', leaveSession)
}

const startLive = async () => {
  if (await startLiveApi(props.liveId)) {
    isStart.value = true
  }
}

const stopLive = async () => {
  await stopLiveApi(props.liveId)
}

const leaveSession = async () => {
  if (session.value) {
    if (userRole === 'PUB' && confirm('라이브를 정말 종료하시겠습니까?')) {
      await stopLive()
      session.value.disconnect()
      router.push(`/live/${props.liveId}/end`)
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

const pubToolBar = reactive([
  { isActive: true, iconName: 'fa-regular fa-file-lines', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-comments', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-rectangle-list', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-circle-question', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-circle-play', click: startLive }
])

const subToolBar = reactive([
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
</script>

<template>
  <template v-if="userRole === 'PUB'">
    <div class="session" v-if="session">
      <section class="col-1">
        <user-video :stream-manager="mainStreamManager" :is-start="isStart" />
        <live-script v-if="pubToolBar[0].isActive" :script="props.script" />
      </section>

      <section class="col-2" v-if="pubToolBar[1].isActive">
        <live-chat />
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
        <user-video :stream-manager="mainStreamManager" :is-start="true" />
      </section>

      <section class="col-2" v-if="subToolBar[0].isActive">
        <live-chat />
      </section>

      <section class="col-3" v-if="subToolBar[1].isActive">
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
