<script setup>
import { onMounted } from 'vue'
import { ref } from 'vue'

const video = ref(null)

const props = defineProps({
  streamManager: Object
})

onMounted(() => {
  props.streamManager.addVideoElement(video.value)
})
</script>

<template>
  <!-- <img src="@/assets/img/live-badge.png" alt="live" class="icon" /> -->
  <div class="camera-box">
    <video ref="video" autoplay />
  </div>
</template>

<style lang="scss" scoped>
.camera-box {
  flex: 3;

  @include box(100%, fit-content, $dark, 20px, 0, 0);
  @include flex-box(center, center, column);

  video {
    height: calc(50vh - 1px);

    // 거울모드
    // transform: rotateY(180deg);
    // -webkit-transform: rotateY(180deg); /* Safari and Chrome */
    // -moz-transform: rotateY(180deg); /* Firefox */
  }
}
</style>
