<script setup>
import { ref } from 'vue'

const props = defineProps(['data'])
const emit = defineEmits(['getData'])

const ph = ref(props.data.title + ' 입력하세요.')
</script>

<template>
  <div class="short-body">
    <label for="input">{{ props.data.title }}</label>
    <div class="input-area">
      <input
        :type="props.data.type"
        :placeholder="ph"
        class="short-input"
        @input="$emit('getData', $event.target.value)"
      />
      <input type="submit" :value="props.data.text" class="input-btn" />
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
