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

<style lang="scss" scoped></style>
