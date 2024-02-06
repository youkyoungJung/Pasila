<script setup>
import { onMounted, ref, reactive } from 'vue'
import router from '@/router'
import { OpenVidu } from 'openvidu-browser'
import { createSession, createToken } from '@/components/api/OpenviduAPI.js'
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

// Join form
const mySessionId = 'SessionA'
const myUserName = 'Participant' + Math.floor(Math.random() * 100)

onMounted(() => {
  joinSession()
})

const clickToolBarBtn = (n) => {
  controlToolBar[n].isActive = !controlToolBar[n].isActive
}

const joinSession = async () => {
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
    console.warn('exception=', exception)
    alert('서버에 문제가 발생했습니다. 잠시후 다시 시도해주세요.')
    router.push('/')
  })

  const res = await getToken(mySessionId)
  const token = res.data

  try {
    await session.value.connect(token, { clientData: myUserName })

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
  const res = await createSession(mySessionId)
  return await createToken(res.data)
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

const product = reactive({
  id: 1,
  description:
    '<h2 class="ql-align-center">[aespa 카리나 착용] <strong>big heart necklace (3color)</strong></h2><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p><br></p><p class="ql-align-center"><strong>big heart necklace (3color)</strong></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center">사랑스러운의 하트 펜던트로 룩의 포인트가 되어 줄 네크리스입니다 :)</p><p class="ql-align-center">적당한 크기로 과하지 않고 은은한 포인트를 더해 줄 아이템이에요</p><p class="ql-align-center">일반 네크리스보다 조금 더 롱하게 내려오는</p><p class="ql-align-center">기장감으로 더욱 유니크하게 착용가능하구요,</p><p class="ql-align-center">여유줄이 있어 간편하게 길이 조절이 가능하답니다</p><p class="ql-align-center">가벼운 무게감으로 부담없이 데일리로 착용하실 수 있으실거에요!</p><p class="ql-align-center">썸머시즌에도 편안하게 착용 가능하여 추천드릴게요♡</p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong style="color: rgb(37, 37, 37);">COLOR</strong><span style="color: rgb(37, 37, 37);">&nbsp;실버, 골드, 블랙</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong style="color: rgb(37, 37, 37);">FABRIC</strong><span style="color: rgb(37, 37, 37);">&nbsp;신주</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong style="color: rgb(37, 37, 37);">비침&nbsp;</strong>X<strong style="color: rgb(37, 37, 37);">&nbsp;/&nbsp;안감&nbsp;</strong>X</p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong style="color: rgb(37, 37, 37);">SIZE</strong><span style="color: rgb(37, 37, 37);">&nbsp;free</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center">(상세사이즈)</p><p class="ql-align-center"><span style="color: rgb(117, 117, 117);">&nbsp;&nbsp;</span></p><p class="ql-align-center"><span style="color: rgb(117, 117, 117);">가로 2 세로 2 끈길이 60 (+5cm)</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong style="color: rgb(37, 37, 37);">MODEL</strong><span style="color: rgb(37, 37, 37);">&nbsp;&nbsp;JUYOUNG&nbsp;(</span><span style="color: rgb(0, 0, 0);">162cm /&nbsp;44,5size / 26inch / 235mm / pants_ s)</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong>NOTICE</strong></p><p class="ql-align-center"><br></p><p class="ql-align-center">모든 의류제품은 드라이 클리닝을 권장합니다</p><p class="ql-align-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p class="ql-align-center"><br></p><p class="ql-align-center"><span style="color: rgb(125, 125, 125);">모든 실측은 단면(cm)으로 측정 되었습니다.</span></p><p class="ql-align-center"><span style="color: rgb(125, 125, 125);">제품 측정법에 따라 1-3cm정도의 오차가 발생 할 수 있습니다.</span></p><p class="ql-align-center"><span style="color: rgb(125, 125, 125);">제품 컬러는 사용자의 모니터 환경에 따라 다소 차이가 있을 수 있으며</span></p><p class="ql-align-center"><span style="color: rgb(125, 125, 125);">모델컷 보다는 상품컷 컬러를 참고 해주세요.</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><span style="color: rgb(125, 125, 125);">어리틀빗에 게시된 모든 컨텐츠들은 저작권법에 의거하여 보호받고</span></p><p class="ql-align-center"><span style="color: rgb(125, 125, 125);">있으며 무단 도용시 저작권법에 의해 법적조치를 받을 수 있습니다.</span></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><br></p><p class="ql-align-center"><strong>ALWAYS ALITTLEBEAT!</strong></p><p><br></p>',
  productOption: [
    { name: 'silver', price: 120000, discountPrice: 65000, stock: 12 },
    { name: 'gold', price: 120000, discountPrice: 65000, stock: 12 },
    { name: 'black', price: 120000, discountPrice: 65000, stock: 12 }
  ]
})
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
        <live-stock v-if="controlToolBar[2].isActive" />
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
        <live-description :description="product.description" :options="product.productOption" />
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
