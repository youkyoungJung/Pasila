<script setup>
import VCategory from '@/components/common/VCategory.vue'
import BestShortping from '@/components/main/BestShortping.vue'
import LiveList from '@/components/main/LiveList.vue'
import { ref } from 'vue'
const categoryIndex = ref(0)
</script>

<template>
  <v-category
    @categoryIndex="
      (e) => {
        categoryIndex = e
      }
    "
  />
  <best-shortping v-if="categoryIndex == 0" />
  <live-list :categoryIndex="categoryIndex" />
</template>

<style lang="scss">
.title {
  @include font-factory($fs-5, bold);
}
</style>
