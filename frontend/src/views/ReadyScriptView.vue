<script setup>
import steps from '@/components/ready/Steps.vue'
import NextButton from '@/components/ready/NextButton.vue'

import { ref } from 'vue'

const step = ref('script')
const nextStep = ref('chatbot')

// const sendProduct = () => {
//   //상품 정보 보내기
// }
</script>

<template>
  <div class="container">
    <steps :data="step" />

    <next-button :data="nextStep" />
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, null, 0, 0.8rem, 0.5rem);
}
</style>
