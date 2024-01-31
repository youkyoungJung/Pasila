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
  questions.value.splice(i, 1)
}
</script>

<template>
  <div class="container">
    <steps :data="step" />
    <div class="chatbot-body">
      <div class="faq">FAQ</div>
      <div v-for="(question, index) in questions" :key="index" class="chatbot-input">
        <div class="chatbot-part">
          <chatbot-input
            @getQuestion="(v) => (question.q = v)"
            @getAnswer="(v) => (question.a = v)"
            :q="question.q"
            :a="question.a"
          />
        </div>
        <div class="remove-part">
          <button class="remove-btn" @click="removeQuestion(index)">ㅡ</button>
        </div>
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
      @include box(41rem, 2rem, white, 0.625rem, 0.3rem, 0);
      cursor: pointer;
      border: 1px solid #d9d9d9;
      color: #ababab;
      font-weight: 500;
    }

    .chatbot-input {
      @include box(41rem, 100%, white, 0.625rem, 0.3rem, 0);
      display: flex;
      justify-content: space-between;
      border: 1px solid #d9d9d9;
      .chatbot-part {
        width: 100%;
      }

      .remove-part {
        width: 8%;
        margin-top: 0.8rem;
        display: flex;
        justify-content: center;
        .remove-btn {
          @include box(2rem, 2rem, white, 50%, 0.1rem, 0.2rem);
          cursor: pointer;
          color: $main;
          border: 1px solid $main;
        }
      }
    }
  }
}
</style>
