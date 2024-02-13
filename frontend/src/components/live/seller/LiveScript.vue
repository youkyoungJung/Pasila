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
  .script {
    @include flex-box();
    height: 100%;
    overflow-y: auto;
  }
}
</style>
