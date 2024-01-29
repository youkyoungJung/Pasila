<script setup>
import steps from '@/components/ready/Steps.vue'
import NextButton from '@/components/ready/NextButton.vue'
import ChatbotInput from '@/components/ready/ChatbotInput.vue'
import { ref } from 'vue'

const step = ref('chatbot')
const nextStep = ref('schedule')
const questions = ref([
  {
    q: '',
    a: ''
  }
])

const addQuestion = () => {
  questions.value.push({ q: '', a: '' })
}

const removeQuestion = (i) => {
  console.log(questions.value)
  console.log(i)
  const temp = questions.value.splice(i, 1)
  console.log(temp)
  console.log(questions.value)
}
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="chatbot-body">
      <div class="faq">FAQ</div>
      <div v-for="(question, index) in questions" :key="index" class="chatbot-input">
        <chatbot-input
          @data="
            (q, a) => {
              question.q = q
              question.a = a
            }
          "
        />
        <button class="remove-btn" @click="removeQuestion(index)">질문 삭제</button>
      </div>
      <button @click="addQuestion" class="add-btn">+ 질문 추가하기</button>
    </div>
    <next-button :data="nextStep" />
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(95%, 100%, none, 0.3rem, 0.8rem, 0.5rem);

  .chatbot-body {
    @include box(100%, 100%, none, 0, 0, 0);
    display: flex;
    flex-direction: column;
    align-items: center;

    .faq {
      @include font-factory($fs-4, bold, $main);
      margin-bottom: 1rem;
    }
    .add-btn {
      @include box(53%, 100%, white, 0.3rem, 0.1rem, 0.1rem);
      cursor: pointer;
      border: 1px solid $main;
      color: $main;
    }

    .chatbot-input {
      @include box(50%, 100%, white, 0.3rem, 0.2rem, 0.5rem);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
      border: 1px solid $light-dark;

      .remove-btn {
        @include box(30%, 10%, white, 0.3rem, 0.1rem, 0.2rem);
        cursor: pointer;
        color: $main;
        border: 1px solid $main;
      }
    }
  }
}
</style>
