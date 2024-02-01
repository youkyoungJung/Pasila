<script setup>
import { RouterView } from 'vue-router'
import VHeader from '@/components/common/VHeader.vue'
</script>

<template>
  <v-header />
  <RouterView />
</template>

<style lang="scss">
body {
  margin: 0;

  .vc-day {
    height: 5rem;
  }

  .vc-title {
    background-color: #d9d9d9;
  }
}
</style>
