<script setup>
import { onMounted, onUnmounted, ref, reactive } from 'vue'
import router from '@/router'
import { OpenVidu } from 'openvidu-browser'
import { createSessionApi, createTokenApi } from '@/components/api/OpenviduAPI.js'
import { getLiveProductApi } from '@/components/api/OpenviduAPI.js'
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

let userRole = ref('SUB')

let product = reactive({})

const props = defineProps(['liveId'])
const { member } = useMemberStore()

onMounted(async () => {
  await getProduct()
  joinSession()
})

onUnmounted(() => {
  leaveSession()
})

const getProduct = async () => {
  product = await getLiveProductApi(props.liveId)
}

const clickToolBarBtn = (n) => {
  controlToolBar[n].isActive = !controlToolBar[n].isActive
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
    console.warn('exception=', exception)
    alert('서버에 문제가 발생했습니다. 잠시후 다시 시도해주세요.')
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
    console.log('There was an error connecting to the session:', error.code, error.message)
  }

  window.addEventListener('beforeunload', leaveSession)
}

const leaveSession = () => {
  if (session.value) {
    if (userRole === 'PUB' && confirm('라이브를 정말 종료하시겠습니까?')) {
      session.value.disconnect()
      router.push(`/live/${props.liveId}/end`)
    } else {
      session.value.disconnect()
    }
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

const controlToolBar = reactive(
  userRole.value === 'PUB'
    ? [
        { isActive: true, iconName: 'fa-regular fa-file-lines', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-regular fa-comments', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-regular fa-rectangle-list', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-regular fa-circle-question', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-solid fa-phone-slash', click: leaveSession }
      ]
    : [
        { isActive: true, iconName: 'fa-regular fa-file-lines', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-regular fa-comments', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-regular fa-rectangle-list', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-regular fa-circle-question', click: clickToolBarBtn },
        { isActive: true, iconName: 'fa-solid fa-phone-slash', click: leaveSession }
      ]
)
</script>

<template>
  <template v-if="userRole === 'PUB'">
    <div class="session" v-if="session">
      <section class="col-1">
        <user-video :stream-manager="mainStreamManager" />
        <live-script v-if="controlToolBar[0].isActive" />
      </section>

      <section class="col-2" v-if="controlToolBar[1].isActive">
        <live-chat />
      </section>

      <section class="col-3" v-if="controlToolBar[2].isActive || controlToolBar[3].isActive">
        <live-stock v-if="controlToolBar[2].isActive" :live-id="props.liveId" />
        <live-question v-if="controlToolBar[3].isActive" />
      </section>
    </div>
  </template>
  <template v-else>
    <div class="session" v-if="session">
      <section class="col-1">
        <user-video :stream-manager="mainStreamManager" />
      </section>

      <section class="col-2" v-if="controlToolBar[1].isActive">
        <live-chat />
      </section>

      <section class="col-3">
        <live-description :product="product" :live-id="props.liveId" />
      </section>
    </div>
  </template>
  <div class="tool-bar">
    <template v-for="(item, index) in controlToolBar" :key="index">
      <tool-bar-btn
        :is-active="item.isActive"
        :icon-name="item.iconName"
        @click-btn="item.click(index)"
      />
    </template>
  </div>
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
