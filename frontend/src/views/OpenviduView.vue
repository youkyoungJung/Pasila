<script setup>
import { onMounted, ref, reactive } from 'vue'
import axios from 'axios'
import router from '@/router'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/components/live/openvidu/UserVideo.vue'
import LiveScript from '@/components/live/seller/LiveScript.vue'
import LiveStock from '@/components/live/seller/LiveStock.vue'
import LiveQuestion from '@/components/live/seller/LiveQuestion.vue'
import LiveChat from '@/components/live/LiveChat.vue'
import ToolBarBtn from '@/components/live/ToolBarBtn.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json'

const APPLICATION_SERVER_URL = 'http://localhost:5000/'

let OV = ref(undefined)
let session = ref(undefined)
let mainStreamManager = ref(undefined)
let publisher = ref(undefined)
let subscribers = ref([])

// Join form
const mySessionId = 'SessionA'
const myUserName = 'Participant' + Math.floor(Math.random() * 100)

onMounted(() => {
  joinSession()
})

const clickToolBarBtn = (n) => {
  controlToolBar[n].isActive = !controlToolBar[n].isActive
}

const joinSession = () => {
  OV.value = new OpenVidu()
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
    alert('서버에 문제가 발생했습니다. 잠시후 다시 시도해주세요.')
    router.push('/')
  })

  getToken(mySessionId).then((token) => {
    session.value
      .connect(token, { clientData: myUserName })
      .then(() => {
        let publisherT = OV.value.initPublisher(undefined, {
          audioSource: undefined,
          videoSource: undefined,
          publishAudio: true,
          publishVideo: true,
          frameRate: 30,
          insertMode: 'APPEND',
          mirror: false
        })

        // this.videoSessionService.generateToken(this.lesson.id).subscribe(
        //   (response) => {
        //     // {0: token}
        //     this.token = response[0]
        //     console.warn('Token: ' + this.token)
        //     this.OPEN_VIDU_CONNECTION()
        //   },
        //   (error) => {
        //     console.log(error)
        //     if (error.status === 409) {
        //       this.snackBar.open('The teacher has not opened the lesson yet!', 'Undo', {
        //         duration: 3000
        //       })
        //       this.location.back()
        //     }
        //   }
        // )

        mainStreamManager.value = publisherT
        publisher.value = publisherT

        session.value.publish(publisher.value)
      })
      .catch((error) => {
        console.log('There was an error connecting to the session:', error.code, error.message)
      })
  })
  window.addEventListener('beforeunload', leaveSession)
}

const leaveSession = () => {
  if (session.value) {
    if (confirm('라이브를 정말 종료하시겠습니까?')) {
      session.value.disconnect()
    } else {
      return
    }
  }

  session.value = undefined
  mainStreamManager.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OV.value = undefined

  window.removeEventListener('beforeunload', leaveSession)
}

const getToken = async (mySessionId) => {
  const sessionId = await createSession(mySessionId)
  return await createToken(sessionId)
}

const createSession = async (sessionId) => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions',
    { customSessionId: sessionId },
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  return response.data // The sessionId
}

const createToken = async (sessionId) => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
    {},
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  return response.data // The token
}

const controlToolBar = reactive([
  { isActive: true, iconName: 'fa-regular fa-file-lines', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-comments', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-rectangle-list', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-regular fa-circle-question', click: clickToolBarBtn },
  { isActive: true, iconName: 'fa-solid fa-phone-slash', click: leaveSession }
])
</script>

<template>
  <div class="session" v-if="session">
    <section class="col-1">
      <user-video :stream-manager="mainStreamManager" />
      <live-script v-if="controlToolBar[0].isActive" />
    </section>

    <section class="col-2" v-if="controlToolBar[1].isActive">
      <live-chat />
    </section>

    <section class="col-3" v-if="controlToolBar[2].isActive || controlToolBar[3].isActive">
      <live-stock v-if="controlToolBar[2].isActive" />
      <live-question v-if="controlToolBar[3].isActive" />
    </section>
  </div>
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
