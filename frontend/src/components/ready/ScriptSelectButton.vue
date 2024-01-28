<script setup>
import { ref } from 'vue'

const props = defineProps({
  word: String
})
const isSelected = ref(false)

const selectBtn = () => {
  isSelected.value = !isSelected
}
</script>

<template>
  <button @click="selectBtn" class="btn">{{ props.word }}</button>
</template>

<style lang="scss" scoped></style>
