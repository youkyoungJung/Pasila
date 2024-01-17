<script setup>
import VCategory from '@/components/common/VCategory.vue';
import VAll from '@/components/category/VAll.vue';
</script>

<template>
  <div>
    <VCategory />
    <VAll />
  </div>
</template>

<style lang="scss">
.title {
  @include font-factory($fs-5, bold);
}
</style>
