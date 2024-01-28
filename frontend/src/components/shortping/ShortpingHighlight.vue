<script setup>
import Highlight from '@/components/shortping/Highlight.vue'
</script>

<template>
  <div class="shortping-highlight">
    <div>
      <h2>나는 하이라이트부분</h2>
    </div>
    <highlight />
  </div>
</template>

<style lang="scss" scoped>
.shortping-highlight {
  @include box(100%, 100%, white, 0, 0, 0.2rem);
  display: flex;
  flex-direction: column;
}
</style>
