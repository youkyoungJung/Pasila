<script setup>
import ReadySteps from '@/components/ready/ReadySteps.vue'
import ScheduleCalendar from '@/components/ready/ScheduleCalendar.vue'
import ScheduleTime from '@/components/ready/ScheduleTime.vue'
import { useReadyLiveStore } from '@/stores/readyLive'
import { ref } from 'vue'
import { sendLiveSchedule } from '@/components/api/LiveAPI'
import { useMemberStore } from '@/stores/member'

const step = ref('schedule')
const store = useReadyLiveStore()
const userStore = useMemberStore()

const liveTitle = ref('')
const date = ref(new Date())
const apm = ref(new Date().getHours() >= 12 ? '오후' : '오전')
const hour = ref(date.value.getHours() > 12 ? date.value.getHours() - 12 : date.value.getHours())
const minute = ref(new Date().getMinutes())
const liveTime = ref({
  title: '',
  liveScheduledAt: '',
  script: store.liveScript
})

const liveFormData = new FormData()
const file = ref()
const reserveLive = async () => {
  liveTime.value.title = liveTitle.value
  if (apm.value == '오후') {
    hour.value = parseInt(hour.value) + 12
  }
  liveTime.value.liveScheduledAt =
    date.value.getFullYear() +
    '-' +
    (date.value.getMonth() + 1).toString().padStart(2, '0') +
    '-' +
    date.value.getDate().toString().padStart(2, '0') +
    'T' +
    hour.value.toString().padStart(2, '0') +
    ':' +
    minute.value.toString().padStart(2, '0') +
    ':00'
  store.liveSchedule = liveTime.value

  const searchSrc = (root) => {
    const arr1 = root.split('img').map((v) => v.includes('src') === true && v.split('src='))
    const arr2 = arr1.map((v) => v && v[1]?.split('></p'))
    return arr2.map((v) => v && v[0].slice(1, v[0]?.length - 1)).filter((v) => v !== false)
  }
  const Base64toServerImage = (fullstring) => {
    const changeStr = fullstring
      .split('>')
      .map((v) => {
        if (v.includes('<p')) {
          return v + '>'
        } else if (v.includes('</p')) {
          return v + '>'
        } else if (v.includes('<img')) {
          return false
        } else {
          return false
        }
      })
      .filter((v) => v !== false)
      .join('')

    return changeStr
  } // <p><img ~~~/></p> => <p></p>
  let base64toFile = (base_data, filename) => {
    let arr = base_data.split(',')
    let mime = arr[0].match(/:(.*?);/)[1]

    if (arr[1]) {
      let bstr = atob(arr[1])
      let n = bstr.length
      let u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      console.log(u8arr)
      return new File([u8arr], filename, { type: 'image/jpeg' })
    }
  }
  searchSrc(store.productDesc).map((v, i) => {
    if (v?.length > 1000) {
      //  "data:image/png;base64~~~"는 1000자를 넘어가기 때문에 + base64만 가져오기 위해서
      const imgBase64 = v
      file.value = base64toFile(imgBase64, 'fileName')
      store.productImage = file

      liveFormData.set('image', file.value)
    }
  })
  searchSrc(store.productDesc).map((v, i) => {
    if (v?.length > 1000) {
      const innerHTML = Base64toServerImage(store.productDesc)
      store.liveProduct.value.description = innerHTML
    }
  })

  await sendData()
}
const sendData = async () => {
  console.log(JSON.stringify(store.liveSchedule))
  console.log(JSON.stringify(store.liveProduct.value))
  console.log(JSON.stringify(store.liveChatbot))
  console.log(store.productImage)
  console.log(userStore.member.id)
  const memberId = {
    memberId: userStore.member.id
  }
  console.log(JSON.stringify(memberId))
  // liveFormData.append('image', '')
  liveFormData.set(
    'live',
    new Blob([JSON.stringify(store.liveSchedule)], {
      type: 'application/json'
    })
  )
  liveFormData.set(
    'product',
    new Blob([JSON.stringify(store.liveProduct.value)], {
      type: 'application/json'
    })
  )

  liveFormData.set(
    'chatbot',
    new Blob([JSON.stringify(store.liveChatbot)], {
      type: 'application/json'
    })
  )
  liveFormData.set('memberId', new Blob([JSON.stringify(memberId)], { type: 'application/json' }))

  const res = await sendLiveSchedule(liveFormData)
  console.log(res)
}
</script>

<template>
  <div class="container">
    <ready-steps :data="step" />
    <input class="title-input" placeholder="라이브 제목을 입력해주세요!" v-model="liveTitle" />
    <div class="body">
      <div class="body-left">
        <schedule-calendar :date="date" @getDate="(v) => (date = v)" />
      </div>
      <div class="body-right">
        <schedule-time
          @getAM="(v) => (apm = v)"
          @getHour="(v) => (hour = v)"
          @getMinute="(v) => (minute = v)"
          :APM="apm"
          :hour="hour"
          :minute="minute"
        />
        <div class="show-time">
          <div class="time-title">라이브 방송 예정 시간은</div>
          <div class="time-body">
            <div v-if="date != ''">
              {{ date.getFullYear() }}년 {{ date.getMonth() + 1 }}월 {{ date.getDate() }}일
            </div>
            <div>{{ apm }} {{ hour }}시{{ minute }}분 입니다.</div>
          </div>
        </div>
        <button @click="reserveLive" class="reserve-btn">예약하기</button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);
  display: flex;
  flex-direction: column;
  align-items: center;

  .title-input {
    width: 80%;
    height: 3rem;
    border: #d9d9d9;
    border-radius: 0.625rem;
    margin-top: 2rem;
    padding-left: 0.5rem;
    background-color: #d9d9d9;
    outline: none;
    font-family: 'Noto Sans KR';
    font-size: 1.3rem;
    font-weight: 500;
  }
  .body {
    @include box(100%, 100%, white, 0, 0.3rem, 0.1rem);
    display: flex;
    justify-content: space-around;
    border-radius: 0.5rem;

    .body-left {
      @include box(40%, 100%, white, 0, 0.1rem, 0.2rem);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
    }

    .body-right {
      @include box(40%, 100%, white, 0, 0.1rem, 0.2rem);
      display: flex;
      flex-direction: column;
      align-items: center;

      .show-time {
        width: 28rem;
        height: 18rem;
        flex-shrink: 0;
        border-radius: 0.625rem;
        border: 5px solid $main;
        color: #000;

        text-align: center;
        font-family: 'Noto Sans KR';
        font-size: 2.5rem;
        font-weight: 500;
        .time-title {
          @include font-factory(1.5rem, 500);
          display: flex;
          justify-content: flex-start;
          padding: 0.3rem;
          margin-left: 1.5rem;
          margin-top: 1.5rem;
          margin-bottom: 2rem;
        }
      }

      .reserve-btn {
        @include font-factory(1.4rem, 500, white);
        width: 28rem;
        height: 4rem;
        border-radius: 0.625rem;
        background: $main;
        border: none;
        cursor: pointer;
        margin-top: 3rem;
      }
    }
  }
}
</style>
