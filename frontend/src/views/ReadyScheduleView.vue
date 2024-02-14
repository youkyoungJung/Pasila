<script setup>
import ReadySteps from '@/components/ready/ReadySteps.vue'
import ScheduleCalendar from '@/components/ready/ScheduleCalendar.vue'
import ScheduleTime from '@/components/ready/ScheduleTime.vue'
import { useReadyLiveStore } from '@/stores/readyLive'
import { ref } from 'vue'

const step = ref('schedule')
const store = useReadyLiveStore()

const liveTitle = ref('')
const date = ref(new Date())
const apm = ref(new Date().getHours() >= 12 ? '오후' : '오전')
const hour = ref(date.value.getHours() > 12 ? date.value.getHours() - 12 : date.value.getHours())
const minute = ref(new Date().getMinutes())
const liveTime = ref({
  title: '',
  liveScheduleAt: '',
  script: store.liveScript
})

const reserveLive = async () => {
  store.liveFormData.append('image', 'hi')
  liveTime.value.title = liveTitle.value
  liveTime.value.liveScheduleAt =
    date.value.getFullYear() +
    '-' +
    (date.value.getMonth() + 1) +
    '-' +
    date.value.getDate() +
    'T' +
    hour.value +
    ':' +
    minute.value +
    ':00'
  store.liveSchedule = liveTime.value

  const res = await store.sendData()
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
