<script setup>
import steps from '@/components/ready/Steps.vue'
import { ref } from 'vue'

const step = ref('register')
</script>

<template>
  <div>
    <steps :data="step" />
  </div>
</template>

<style lang="scss" scoped></style>
