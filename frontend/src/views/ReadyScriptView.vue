<script setup>
import steps from '@/components/ready/Steps.vue'
import NextButton from '@/components/ready/NextButton.vue'

import { ref } from 'vue'

const step = ref('script')
const nextStep = ref('chatbot')
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="next-btn">
      <next-button :data="nextStep" />
    </div>
    <div class="body"></div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);

  .next-btn {
    @include box(100%, null, 0, 0, 0, 0);
    @include flex-box($justify: flex-end);
  }

  .body {
    @include box(100%, 80%, none, 0, 0.3rem, 0.1rem);
    @include flex-box($align: flex-start, $justify: space-evenly);
    border: 1px solid $main;
    border-radius: 0.5rem;
  }
}
</style>
