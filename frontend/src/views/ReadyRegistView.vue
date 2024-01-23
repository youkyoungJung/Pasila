<script setup>
import steps from '@/components/ready/Steps.vue'
import ProductInput from '@/components/ready/ProductInput.vue'
import NextButton from '@/components/ready/NextButton.vue'

import { ref } from 'vue'

const step = ref('register')
const nextStep = ref('script')

// const sendProduct = () => {
//   //상품 정보 보내기
// }
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="body">
      <product-input />
      <div class="preview">미리보기페이지 히히</div>
    </div>
    <div class="next-btn">
      <next-button :data="nextStep" @click="sendProduct" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0, 0.8rem, 0.5rem);
  .body {
    @include box(100%, 80%, none, 0, 0.3rem, 0.1rem);
    @include flex-box($align: flex-start, $justify: space-evenly);
    border: 1px solid $main;
    border-radius: 0.5rem;

    .preview {
      @include box(70%, 100%, yellow, 0, 0.8rem, 0.8rem);
      @include flex-box($align: flex-start);
      @include font-factory($fs-1, null);
    }
  }
  .next-btn {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
