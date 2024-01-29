<script setup>
import steps from '@/components/ready/Steps.vue'
import NextButton from '@/components/ready/NextButton.vue'
import ChatbotInput from '@/components/ready/ChatbotInput.vue'
import { ref } from 'vue'

const step = ref('chatbot')
const nextStep = ref('schedule')
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div>
      <chatbot-input />
    </div>
    <next-button :data="nextStep" />
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);
}
</style>
