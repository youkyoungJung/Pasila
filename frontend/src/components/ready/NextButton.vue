<script setup>
import { ref, defineProps } from 'vue'
import router from '@/router'

const props = defineProps({
  data: String
})

const goNext = () => {
  console.log(props.product)
  router.push(`/ready/${props.data}`)
}
</script>

<template>
  <div class="btn-container">
    <button @click="goNext" class="next-btn">다음 단계 ></button>
  </div>
</template>

<style lang="scss" scoped>
.btn-container {
  display: flex;
  justify-content: flex-end;
}
.next-btn {
  @include font-factory($fs-1, null);
  @include box(8rem, 3rem, $main, 0.7rem, 0.3rem, 0.1rem);
  border: 1px solid $main;
  color: white;
  cursor: pointer;
}
</style>
