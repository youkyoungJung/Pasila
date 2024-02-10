<script setup>
import { ref } from 'vue'

const props = defineProps(['data', 'inputData'])
const emit = defineEmits(['getData', 'sendData'])

const ph = ref(props.data.title + ' 입력하세요.')

const clicked = () => {
  console.log('눌렀따고')
  emit('sendData', props.data)
}
</script>

<template>
  <div class="short-body">
    <label for="short-input">{{ props.data.title }}</label>
    <div class="input-area">
      <input
        id="short-input"
        :type="props.data.type"
        :placeholder="ph"
        class="short-input"
        @input="$emit('getData', $event.target.value)"
        :value="props.inputData"
      />
      <input type="button" :value="props.data.text" class="input-btn" @click="clicked()" />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.short-body {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 0.2rem;

  label {
    width: 90%;
    display: flex;
    justify-content: flex-start;
  }
  .input-area {
    @include flex-box($justify: space-between);
    width: 92%;
    margin: 0.2rem 0;

    .short-input {
      @include box(90%, 2.5rem, whitesmoke, 0, 0, 0);
      border: none;
      padding-left: 0.5rem;
      outline: none;
    }

    .input-btn {
      @include font-factory(0.8rem, null);
      border: 1px solid $dark;
      background-color: white;
      padding: 0.5rem;
      margin-left: 0.5rem;
      cursor: pointer;
    }

    ::-webkit-outer-spin-button,
    ::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }
  }
}
</style>
