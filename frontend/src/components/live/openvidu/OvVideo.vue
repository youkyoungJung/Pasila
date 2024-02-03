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
  <video ref="video" autoplay />
</template>

<style lang="scss" scoped>
video {
  height: 100%;
  border-radius: 20px;

  // 거울모드
  // transform: rotateY(180deg);
  // -webkit-transform: rotateY(180deg); /* Safari and Chrome */
  // -moz-transform: rotateY(180deg); /* Firefox */
}
</style>
