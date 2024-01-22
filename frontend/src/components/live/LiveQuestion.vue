<script setup>
const QList = ['제품 기능', '제품 설명', '사이즈 정보']
</script>

<template>
  <div class="live-question">
    <h1>질문 리스트</h1>
    <h2>시청자들이 답변을 원하는 질문들이에요!</h2>
    <div class="question-list">
      <div v-for="(item, index) in QList" :key="index" class="question">{{ item }}</div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-question {
  @include box(null, 100%, none, 20px, 1rem 0 1rem 0, 1rem);
  border: 3px solid $main;
  h1 {
    @include font-factory($fs-5, bold);
    margin: 0;
  }
  h2 {
    @include font-factory($fs-1, 500);
    margin: 0;
  }
  .question-list {
    @include box(100%, 38rem, null, 0, 1rem 0 0 0, 0);
    @include flex-box(center, flex-start, column);
    .question {
      @include box(80%, fit-content, null, 30px, 0.2rem 1rem, 1rem);
      @include font-factory($fs-4, bold);
      text-align: center;
    }
    :nth-child(even) {
      background-color: $soft-pink;
    }
    :nth-child(odd) {
      background-color: $yellow;
    }
  }
}
</style>
