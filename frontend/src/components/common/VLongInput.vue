<script setup>
import { ref } from 'vue'

const props = defineProps(['data'])
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
      :value="props.data.value"
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

  .label {
    width: 90%;
    display: flex;
    justify-content: flex-start;
    text-align: flex-start;
  }

  input {
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
