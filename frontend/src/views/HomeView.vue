<script setup>
import VCategory from '@/components/common/VCategory.vue'
import BestShortping from '@/components/main/BestShortping.vue'
import LiveList from '@/components/main/LiveList.vue'
</script>

<template>
  <v-category />
  <best-shortping />
  <live-list />
</template>

<style lang="scss">
.title {
  @include font-factory($fs-5, bold);
}
</style>
