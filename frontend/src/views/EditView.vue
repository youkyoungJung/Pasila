<script setup>
import { ref } from 'vue'

import ShortpingVideo from '@/components/shortping/ShortpingVideo.vue'
import ShortpingHighlight from '@/components/shortping/ShortpingHighlight.vue'

const shortping = ref({
  title: ''
})
const highlights = ref([
  {
    isEnroll: true,
    highlightTitle: '첫 인사',
    highlightStartTime: '00:00:01',
    highlightEndTime: '00:00:02',
    highlightSubtitle: '안녕하세요! 오늘은 신상 니트를 소개해 드릴게요.'
  },
  {
    isEnroll: true,
    highlightTitle: '앙고라 니트 - 핑크 컬러',
    highlightStartTime: '00:00:04',
    highlightEndTime: '00:00:05',
    highlightSubtitle: '뽀용한 색감의 핑크컬러! 청바지에 찰떡'
  },
  {
    isEnroll: true,
    highlightTitle: '앙고라 니트 - 블랙 컬러',
    highlightStartTime: '00:00:07',
    highlightEndTime: '00:00:08',
    highlightSubtitle: '어디에나 입기 좋은 블랙 컬러! 하나쯤 가지고 있으면 활용성 갑'
  }
])
const complete = () => {
  //제작 완료버튼
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
      <suspense timeout="0">
        <div class="show-highlight">
          <shortping-highlight
            :data="highlights"
            @addEmptyData="(e) => highlights.push(e)"
            @deleteData="(e) => highlights.splice(e, 1)"
            @getData="(e) => (highlights[e].isEnroll = true)"
            @video="
              (e) => {
                console.log(video)
                video = e
              }
            "
          />
          <button @click="complete" class="complete-btn">제작 완료</button>
        </div>
      </suspense>
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
