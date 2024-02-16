<script setup>
import { RouterView } from 'vue-router'
import VHeader from '@/components/common/VHeader.vue'
</script>

<template>
  <v-header />
  <router-view class="myapp" />
</template>

<style lang="scss">
body {
  margin: 0;
  overflow-x: hidden;

  .vc-day {
    height: 5rem;
  }

  .vc-title {
    background-color: #d9d9d9;
  }

  &::-webkit-scrollbar {
    width: 0.3rem;
  }

  &::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: $light-dark; /* 스크롤바의 색상 */
    border-radius: 10px;
  }
}
</style>
