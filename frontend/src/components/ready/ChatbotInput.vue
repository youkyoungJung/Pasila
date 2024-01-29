<script setup>
import { ref, watch } from 'vue'

const question = ref('')
const answer = ref('')
const emit = defineEmits(['data'])
const saveQuestion = () => {
  emit('data', { q: question, a: answer })
  console.log(emit)
}
</script>

<template>
  <div class="chatbot-box">
    <div class="text-box">
      <label for="question">질문</label>
      <input
        id="question"
        type="text"
        placeholder="질문을 입력해주세요."
        class="input-box"
        v-model="question"
      />
    </div>
    <div class="text-box">
      <label for="answer">답변</label>
      <input
        id="answer"
        type="text"
        placeholder="답변을 입력해주세요."
        class="input-box"
        v-model="answer"
      />
    </div>
    <button class="save-btn" @click="saveQuestion()">질문 저장</button>
  </div>
</template>

<style lang="scss" scoped>
.chatbot-box {
  @include box(100%, 100%, white, 0.3rem, 0.2rem, 0.5rem);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;

  .text-box {
    width: 100%;
    display: flex;
    padding: 0.5rem;
  }
  label {
    margin: 0 1rem;
  }
  .input-box {
    @include box(80%, 100%, $light-gray, 0.3rem, 0.1rem, 0.1rem);
    border: none;
    outline: none;
  }
  .save-btn {
    @include box(30%, 10%, white, 0.3rem, 0.1rem, 0.2rem);
    cursor: pointer;
    color: $main;
    border: 1px solid $main;
  }
}
</style>
