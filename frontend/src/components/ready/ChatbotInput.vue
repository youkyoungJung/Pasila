<script setup>
import { ref, watch } from 'vue'

const question = ref('')
const answer = ref('')
const isSaved = ref(false)
const emit = defineEmits(['data'])
const props = defineProps({ q: String, a: String })

watch([() => props.q, () => props.a], ([q, a]) => {
  if (q !== '' && a != '') {
    question.value = q
    answer.value = a
  } else {
    question.value = ''
    answer.value = ''
    isSaved.value = false
  }
})

const saveQuestion = () => {
  if (question.value == '' || answer.value == '') alert('질문과 답변을 입력해주세요!')
  else {
    emit('data', { q: question.value, a: answer.value })
    isSaved.value = true
  }
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
    <button class="saved-btn" v-if="isSaved">저장 완료</button>
    <button @click="saveQuestion" class="none-save-btn" v-else>질문 저장</button>
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
  .none-save-btn {
    @include box(30%, 10%, white, 0.3rem, 0.1rem, 0.2rem);
    cursor: pointer;
    color: $main;
    border: 1px solid $main;
  }

  .saved-btn {
    @include box(30%, 10%, $main, 0.3rem, 0.1rem, 0.2rem);
    color: white;
    border: 1px solid $main;
  }
}
</style>
