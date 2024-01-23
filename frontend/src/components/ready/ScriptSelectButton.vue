<script setup>
import { ref, defineProps } from 'vue'

const props = defineProps({
  word: String
})
const isSelected = ref(false)

const selectBtn = () => {
  isSelected.value = !isSelected
}
</script>

<template>
  <div>
    <button @click="selectBtn" class="btn">{{ props.word }}</button>
  </div>
</template>

<style lang="scss" scoped>
.btn {
  @include box(5rem, 2rem, $light-gray, 0.3rem, 0.3rem, 0.3rem);
  border: none;
  cursor: pointer;
}
.select-btn {
  @include box(5rem, 2rem, $main, 0.3rem, 0.3rem, 0.3rem);
  border: none;
  cursor: pointer;
}
</style>
