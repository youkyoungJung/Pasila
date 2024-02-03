<script setup>
import OvVideo from '@/components/live/openvidu/OvVideo.vue'

defineProps({
  streamManager: Object
})
</script>

<template>
  <div class="camera-box">
    <ov-video v-if="streamManager" :stream-manager="streamManager" />
  </div>
</template>

<style lang="scss" scoped>
.camera-box {
  flex: 3;

  @include box(100%, 50vh, $dark, 20px, 0, 0);
  @include flex-box(center, center, column);
}
</style>
