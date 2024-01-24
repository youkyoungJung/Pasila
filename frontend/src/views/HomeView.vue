<script setup>
import VCategory from '@/components/common/VCategory.vue'
import BestShortping from '@/components/main/BestShortping.vue'
import Live from '@/components/main/Live.vue'
</script>

<template>
  <v-category />
  <best-shortping />
  <live />
</template>

<style lang="scss">
.title {
  @include font-factory($fs-5, bold);
}
</style>
