<script setup>
import { ref, onMounted } from 'vue'
import { getHighlightApi, sendShortpingApi } from '@/components/api/ShortpingAPI'

import ShortpingVideo from '@/components/shortping/ShortpingVideo.vue'
import ShortpingHighlight from '@/components/shortping/ShortpingHighlight.vue'

const video = ref('')
const formData = new FormData()
const sendData = ref({})

const shortping = ref({
  title: ''
})
const highlights = ref([
  {
    isEnroll: true,
    highlightTitle: '첫 인사',
    highlightStartTime: '00:00:01',
    highlightEndTime: '00:00:03'
  },
  {
    isEnroll: true,
    highlightTitle: '앙고라 니트 - 핑크 컬러',
    highlightStartTime: '00:00:05',
    highlightEndTime: '00:00:08'
  },
  {
    isEnroll: true,
    highlightTitle: '앙고라 니트 - 블루 컬러',
    highlightStartTime: '00:00:10',
    highlightEndTime: '00:00:13'
  }
])

onMounted(() => {
  getHighlightDatas()
})

const getHighlightDatas = () => {
  //하이라이트 추천받기(라이브아이디)
  // const res = getHighlightApi(id)
  //console.log(res)
  //highlightTitle == title
  //highlightStartTime == start
  //highlightEndTime == end
  //isEnroll = true로 해주기
}
const sortHighlight = (e) => {
  highlights.value[e].isEnroll = true
  highlights.value.sort((o1, o2) => {
    if (o1.isEnroll && o2.isEnroll) {
      const startTimeComparison = o1.highlightStartTime.localeCompare(o2.highlightStartTime)
      if (startTimeComparison === 0) {
        return o1.highlightEndTime.localeCompare(o2.highlightEndTime)
      }
      return startTimeComparison
    }
    return 0
  })
}
const complete = () => {
  console.log(video.value)
  formData.append('video', video.value)
  sendData.value = {
    title: shortping.value.title,
    productId: '20FD88R7Y5XM',
    livelogs: []
  }
  for (let i = 0; i < highlights.value.length; i++) {
    if (highlights.value[i].isEnroll) {
      sendData.value.livelogs[i] = {
        title: highlights.value[i].highlightTitle,
        start: highlights.value[i].highlightStartTime,
        end: highlights.value[i].highlightEndTime
      }
    }
  }
  console.log(JSON.stringify(sendData.value))
  formData.append(
    'shortpingRequest',
    new Blob([JSON.stringify(sendData.value)], { type: 'application/json' })
  )
  const res = sendShortpingApi(formData)
  console.log(res)
}
</script>

<template>
  <div class="container">
    <div class="short-title">
      <span style="color: #ff495c">숏핑 제작</span>
      <span class="short-subtitle"
        >AI가 선택한 하이라이트를 확인하고 편집해 나만의 숏핑을 만들어보세요!</span
      >
    </div>
    <div class="shortping-body">
      <div class="show-video">
        <input
          type="text"
          placeholder="숏핑의 제목을 입력하세요."
          id="shortpingTitle"
          v-model="shortping.title"
        />
        <shortping-video :data="highlights" :video="video" />
      </div>

      <div class="show-highlight">
        <shortping-highlight
          :data="highlights"
          @addEmptyData="(e) => highlights.push(e)"
          @deleteData="(e) => highlights.splice(e, 1)"
          @getData="(e) => sortHighlight(e)"
          @video="
            (e) => {
              video = e
            }
          "
        />
        <button @click="complete" class="complete-btn">제작 완료</button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include font-factory($fs-1, null, null);
  padding: 1rem;

  .short-title {
    @include font-factory($fs-4, bold);
    margin-bottom: 1rem;

    .short-subtitle {
      @include font-factory($fs-1, 400);
      margin-left: 5px;
    }
  }

  .shortping-body {
    @include box(100%, 100%, white, 0, 0, 0);
    display: flex;
    align-items: flex-start;
    justify-content: space-around;
    .show-video {
      @include box(58%, 100%, white, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;

      input {
        @include box(70%, 2rem, $light-gray, 0.2rem, 0.1rem, 0.1rem);
        @include drop-shadow;
        padding-left: 1rem;
        border: none;
        outline: none;
        margin-top: 1rem;
      }
    }

    .show-highlight {
      @include box(38%, 100%, white, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
    }
  }
  .complete-btn {
    @include box(30%, 2rem, $main, 0.3rem, 0.2rem, 0.2rem);
    border: none;
    color: white;
    cursor: pointer;
  }
}
</style>
