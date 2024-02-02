<script setup>
import OvVideo from '@/components/live/openvidu/OvVideo.vue'

defineProps({
  streamManager: Object
})
</script>

<template>
  <ov-video v-if="streamManager" :stream-manager="streamManager" />
</template>

<style lang="scss" scoped></style>
