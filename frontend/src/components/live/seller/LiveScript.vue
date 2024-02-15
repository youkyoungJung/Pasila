<script setup>
defineProps({
  script: String
})
</script>

<template>
  <div class="script-box">
    <div class="script">{{ script }}</div>
  </div>
</template>

<style lang="scss" scoped>
.script-box {
  flex: 1;
  @include box(calc(100% - 2rem), calc(28vh - 3rem), $gray, 20px, 0, 1rem);
  max-height: calc(28vh - 6rem);
  .script {
    height: 100%;
    overflow-y: auto;

    &::-webkit-scrollbar {
      width: 0.3rem;
    }

    &::-webkit-scrollbar-thumb {
      height: 30%; /* 스크롤바의 길이 */
      background: $light-dark; /* 스크롤바의 색상 */
      border-radius: 10px;
    }
  }
}
</style>
