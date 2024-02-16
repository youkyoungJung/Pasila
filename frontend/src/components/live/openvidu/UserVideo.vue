<script setup>
import { ref } from 'vue'
import OvVideo from '@/components/live/openvidu/OvVideo.vue'

defineProps({
  streamManager: Object,
  isStart: Boolean,
  cnt: Number
})
</script>

<template>
  <div class="camera-header">
    <img v-if="isStart" src="@/assets/img/live-badge.png" alt="live" />
    <img v-else src="@/assets/img/disable-live-badge.png" alt="live" />
  </div>
  <div class="camera-box">
    <ov-video v-if="streamManager" :stream-manager="streamManager" />
  </div>
</template>

<style lang="scss" scoped>
.camera-header {
  @include flex-box(flex-end, space-between);
  width: 100%;

  .live-count {
    display: flex;
    gap: 0.5rem;
    align-items: center;

    span {
      @include font-factory($fs-1, 500);
    }
  }
}
.camera-box {
  flex: 3;

  @include box(100%, 50vh, $dark, 20px, 0, 0);
  @include flex-box(center, center, column);
}
</style>
