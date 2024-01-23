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
  <div>
    <button @click="goNext" class="next-btn">다음 단계 ></button>
  </div>
</template>

<style lang="scss" scoped>
.next-btn {
  @include font-factory($fs-1, null);
  @include box(10rem, null, $main, 0.7rem, 0.5rem, 0.8rem);
  border: 1px solid $main;
  color: white;
  cursor: pointer;
}
</style>
