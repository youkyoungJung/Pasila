<script setup>
import { ref } from 'vue'

const props = defineProps(['data', 'inputData'])
defineEmits(['getData'])

const ph = ref(props.data.title + ' 입력하세요.')
</script>

<template>
  <div class="long-body">
    <label for="longInput" class="label">{{ props.data.title }}</label>
    <input
      id="longInput"
      :type="props.data.type"
      :placeholder="ph"
      @input="$emit('getData', $event.target.value)"
      :value="props.inputData"
      :class="[props.data.title == '이메일' ? 'email-readonly' : 'long-input']"
    />
  </div>
</template>

<style lang="scss" scoped>
.long-body {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 0.2rem;
  .email-readonly {
    pointer-events: none;
    @include box(90%, 2.5rem, whitesmoke, 0, 0, 0);
    border: none;
    padding-left: 0.5rem;
    outline: none;
  }
  .label {
    width: 90%;
    display: flex;
    justify-content: flex-start;
    text-align: flex-start;
  }

  .long-input {
    @include box(90%, 2.5rem, whitesmoke, 0, 0, 0);
    border: none;
    margin-top: 0.2rem;
    padding-left: 0.5rem;
    outline: none;
  }
  ::-webkit-outer-spin-button,
  ::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
}
</style>
