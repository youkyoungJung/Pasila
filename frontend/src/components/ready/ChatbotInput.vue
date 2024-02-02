<script setup>
defineEmits(['getQuestion', 'getAnswer'])
const props = defineProps({ q: String, a: String })
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
        :value="props.q"
        @input="$emit('getQuestion', $event.target.value)"
      />
    </div>
    <div class="text-box">
      <label for="answer">답변</label>
      <input
        id="answer"
        type="text"
        placeholder="답변을 입력해주세요."
        class="input-box"
        :value="props.a"
        @input="$emit('getAnswer', $event.target.value)"
      />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.chatbot-box {
  width: 100%;
  height: 8.25rem;
  flex-shrink: 0;
  border-radius: 0.625rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;

  .text-box {
    width: 100%;
    padding: 0.5rem;
    font-weight: bold;
    font-family: 'Noto Sans KR';
    font-size: 1rem;
    font-style: normal;
    line-height: normal;
  }
  label {
    margin: 0 1rem;
  }
  .input-box {
    width: 88%;
    height: 2.75rem;
    flex-shrink: 0;
    padding-left: 0.3rem;
    background-color: #d9d9d9;
    border-radius: 0.625rem;
    border: none;
    outline: none;
  }
}
</style>
