<script setup>
defineProps({
  questionList: Array
})
</script>

<template>
  <div class="live-question">
    <h1>실시간 질문 리스트</h1>
    <h2>시청자들이 답변을 원하는 질문들이에요.</h2>
    <div class="question-list">
      <div v-for="(item, index) in questionList" :key="index" class="question">{{ item }}</div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-question {
  flex: 1;

  @include box(null, calc(52vh - 4rem - 4px), none, 20px, 0, 1rem);
  border: 3px solid $main;
  h1 {
    @include font-factory($fs-2, bold);
    margin: 0;
  }
  h2 {
    @include font-factory($fs-1, 500);
    margin: 0;
  }
  .question-list {
    @include box(null, 80%, null, 0, 1rem 0 0 0, 0);
    @include flex-box(center, flex-start, column);
    overflow-y: auto;
    .question {
      @include box(70%, fit-content, null, 30px, 0.2rem 1rem, 1rem);
      @include font-factory($fs-3, bold);
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
