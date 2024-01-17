<script setup>
import { RouterView } from 'vue-router'
import VHeader from '@/components/common/VHeader.vue'
</script>

<template>
  <v-header />
  <RouterView />
</template>

<style>
body {
  margin: 0;
}
</style>
