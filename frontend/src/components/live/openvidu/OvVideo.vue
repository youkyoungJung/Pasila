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
    <div class="camera">
      <video ref="video" autoplay />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.camera-box {
  @include box(100%, fit-content, $dark, 20px, 0, 0);
  @include flex-box();
  .camera {
    @include box(fit-content, fit-content, $dark, 20px, 0, 0);
  }

  video {
    height: 50vh;
    border-radius: 20px;
    // transform: rotateY(180deg);
    // -webkit-transform: rotateY(180deg); /* Safari and Chrome */
    // -moz-transform: rotateY(180deg); /* Firefox */
  }
}
</style>
