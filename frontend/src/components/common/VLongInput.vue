<script setup>
import { ref } from 'vue'

const props = defineProps(['data'])
const emit = defineEmits(['getData'])

const ph = ref(props.data.title + ' 입력하세요.')
</script>

<template>
  <div class="long-body">
    <label for="input" class="label">{{ props.data.title }}</label>
    <input
      :type="props.data.type"
      :placeholder="ph"
      @input="$emit('getData', $event.target.value)"
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
}
</style>
