<script setup>
import { ref } from 'vue'
import NewHighlight from '@/components/shortping/NewHighlight.vue'
import Highlight from '@/components/shortping/HighLight.vue'

const highlights = ref([
  {
    isEnroll: true,
    highlightTitle: '첫 인사',
    highlightStartTime: '00:00:02',
    highlightEndTime: '00:00:05',
    highlightSubtitle: '안녕하세요! 오늘은 신상 니트를 소개해 드릴게요.'
  },
  {
    isEnroll: true,
    highlightTitle: '앙고라 니트 - 핑크 컬러',
    highlightStartTime: '00:01:05',
    highlightEndTime: '00:01:25',
    highlightSubtitle: '뽀용한 색감의 핑크컬러! 청바지에 찰떡'
  },
  {
    isEnroll: true,
    highlightTitle: '앙고라 니트 - 블랙 컬러',
    highlightStartTime: '00:01:35',
    highlightEndTime: '00:01:58',
    highlightSubtitle: '어디에나 입기 좋은 블랙 컬러! 하나쯤 가지고 있으면 활용성 갑'
  },
  {
    isEnroll: true,
    highlightTitle: '할인 정보',
    highlightStartTime: '00:13:02',
    highlightEndTime: '00:13:25',
    highlightSubtitle: '오늘 단 하루 15% 할인! 놓치지 마세요!'
  },
  {
    isEnroll: false,
    highlightTitle: '',
    highlightStartTime: '',
    highlightEndTime: '',
    highlightSubtitle: ''
  }
])

const addHighlight = () => {
  //하이라이트추가
}
</script>

<template>
  <div class="shortping-highlight">
    <div class="highlight-top">
      <span>하이라이트</span>
      <button @click="addHighlight">추가</button>
    </div>
    <div class="highlight-body">
      <div v-for="(highlight, index) in highlights" key="index" class="highlights">
        <div v-if="highlight.isEnroll">
          <Highlight :data="highlight" />
        </div>
        <div v-else>
          <new-highlight />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.shortping-highlight {
  @include box(100%, 10%, white, 0, 0, 0.2rem);
  display: flex;
  flex-direction: column;
  align-items: center;

  .highlight-top {
    @include box(95%, 100%, white, 0, 0, 0.2rem);
    display: flex;
    justify-content: space-between;

    span {
      font-weight: bold;
    }

    button {
      width: 10%;
      border: none;
      background-color: $dark;
      color: white;
      border-radius: 0.2rem;
      font-size: 0.7rem;
      cursor: pointer;
    }
  }

  .highlight-body {
    @include box(100%, 32rem, null, 0, 0, 0);
    overflow: auto;
  }
  .highlight-body::-webkit-scrollbar {
    width: 0.3rem;
  }

  .highlight-body::-webkit-scrollbar-thumb {
    height: 20%;
    background: $main;
    border-radius: 10px;
  }

  .highlights {
    margin: 0.1rem;
    padding: 0.1rem;
  }
}
</style>
