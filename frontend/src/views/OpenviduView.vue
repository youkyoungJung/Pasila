<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'
import router from '@/router'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/components/live/openvidu/UserVideo.vue'
import OvVideo from '@/components/live/openvidu/OvVideo.vue'
import LiveScript from '@/components/live/LiveScript.vue'
import LiveStock from '@/components/live/LiveStock.vue'
import LiveChat from '@/components/live/LiveChat.vue'
import LiveQuestion from '@/components/live/LiveQuestion.vue'

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

const controlToolBar = ref([true, true, true, true, true])

onMounted(() => {
  joinSession()
})

const joinSession = () => {
  // --- 1) Get an OpenVidu object ---
  OV.value = new OpenVidu()

  // --- 2) Init a session ---
  session.value = OV.value.initSession()

  // --- 3) Specify the actions when events take place in the session ---

  // On every new Stream received...
  session.value.on('streamCreated', ({ stream }) => {
    const subscriber = session.value.subscribe(stream)
    subscribers.value.push(subscriber)
  })

  // On every Stream destroyed...
  session.value.on('streamDestroyed', ({ stream }) => {
    const index = subscribers.value.indexOf(stream.streamManager, 0)
    if (index >= 0) {
      subscribers.value.splice(index, 1)
    }
  })

  // On every asynchronous exception...
  session.value.on('exception', ({ exception }) => {
    alert('서버에 문제가 발생했습니다. 잠시후 다시 시도해주세요.')
    router.push('/')
  })

  // --- 4) Connect to the session with a valid user token ---

  // Get a token from the OpenVidu deployment
  getToken(mySessionId).then((token) => {
    // First param is the token. Second param can be retrieved by every user on event
    // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
    session.value
      .connect(token, { clientData: myUserName })
      .then(() => {
        // --- 5) Get your own camera stream with the desired properties ---

        // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
        // element: we will manage it on our own) and with the desired properties
        let publisherT = OV.value.initPublisher(undefined, {
          audioSource: undefined, // The source of audio. If undefined default microphone
          videoSource: undefined, // The source of video. If undefined default webcam
          publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
          publishVideo: true, // Whether you want to start publishing with your video enabled or not
          // resolution: '640x480', // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
          mirror: false // Whether to mirror your local video or not
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

        // Set the main video in the page to display our webcam and store our Publisher
        mainStreamManager.value = publisherT
        publisher.value = publisherT

        // --- 6) Publish your stream ---

        session.value.publish(publisher.value)
      })
      .catch((error) => {
        console.log('There was an error connecting to the session:', error.code, error.message)
      })
  })
  window.addEventListener('beforeunload', leaveSession)
}

const leaveSession = () => {
  // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
  if (session.value) session.value.disconnect()

  // Empty all properties...
  session.value = undefined
  mainStreamManager.value = undefined
  publisher.value = undefined
  subscribers.value = []
  OV.value = undefined

  // Remove beforeunload listener
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
</script>

<template>
  <div class="session" v-if="session">
    <section class="col-1">
      <user-video :stream-manager="mainStreamManager" />
      <transition>
        <live-script v-if="controlToolBar[0]" />
      </transition>
    </section>
    <transition>
      <section class="col-2" v-if="controlToolBar[1]">
        <live-chat />
      </section>
    </transition>
    <transition>
      <section class="col-3" v-if="controlToolBar[2] || controlToolBar[3]">
        <transition>
          <live-stock v-if="controlToolBar[2]" />
        </transition>
        <transition>
          <live-question v-if="controlToolBar[3]" />
        </transition>
      </section>
    </transition>
  </div>
  <div class="tool-bar">
    <button
      @click="() => (controlToolBar[0] = !controlToolBar[0])"
      :class="{ active: controlToolBar[0] }"
    >
      <font-awesome-icon icon="fa-regular fa-file-lines" />
    </button>
    <button
      @click="() => (controlToolBar[1] = !controlToolBar[1])"
      :class="{ active: controlToolBar[1] }"
    >
      <font-awesome-icon icon="fa-regular fa-comments" />
    </button>
    <button
      @click="() => (controlToolBar[2] = !controlToolBar[2])"
      :class="{ active: controlToolBar[2] }"
    >
      <font-awesome-icon icon="fa-regular fa-rectangle-list" />
    </button>
    <button
      @click="() => (controlToolBar[3] = !controlToolBar[3])"
      :class="{ active: controlToolBar[3] }"
    >
      <font-awesome-icon icon="fa-regular fa-circle-question" />
    </button>
    <button>방송 종료</button>
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

  button {
    @include box(3rem, 3rem, $dark, 50%, 1rem, 0);
    @include flex-box();
    outline: none;
    border: none;
    cursor: pointer;

    svg {
      width: 1.3rem;
      height: 1.3rem;
      color: white;
    }

    &.active {
      background-color: $main;
    }
  }
}

.v-leave-active {
  // transform: scale(0);
  // transition: 0.5s;
}

.v-enter-active {
  // transform: scale(0);
  // transition: 0.5s;
}

.v-enter-from {
  // transform: scale(1);
  // transition: 0.5s;
}
.v-leave-to {
  // transform: scale(0);
}
</style>
