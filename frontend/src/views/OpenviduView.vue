<script setup>
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/components/live/openvidu/UserVideo.vue'
import { onMounted, ref } from 'vue'

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
    console.warn(exception)
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
          resolution: '640x480', // The resolution of your video
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
  <div id="session" v-if="session">
    <div id="session-header">
      <h1 id="session-title">{{ mySessionId }}</h1>
      <input
        class="btn btn-large btn-danger"
        type="button"
        id="buttonLeaveSession"
        @click="leaveSession"
        value="Leave session"
      />
    </div>
    <div id="main-video" class="col-md-6">
      <user-video :stream-manager="mainStreamManager" />
    </div>
    <div id="video-container" class="col-md-6">
      <user-video :stream-manager="publisher" />
      <user-video
        v-for="sub in subscribers"
        :key="sub.stream.connection.connectionId"
        :stream-manager="sub"
      />
    </div>
  </div>
</template>

<style lang="css" scoped></style>
