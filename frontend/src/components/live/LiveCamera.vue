<script setup>
import { onMounted, ref } from 'vue'

const video = ref(null)
const streaming = ref(false)

const getMediaStream = () => {
  window.navigator.mediaDevices
    .getUserMedia({ video: true, audio: true })
    .then((stream) => {
      video.value.srcObject = stream
      video.value.play()
    })
    .catch((err) => {
      console.error(`error occurred : ${err}`)
    })
}

const playVideo = () => {
  console.log('play video!!')
  if (!streaming.value) {
    streaming.value = true
  }
}

onMounted(() => {
  getMediaStream()
})
</script>

<template>
  <img src="@/assets/img/live-badge.png" alt="live" class="icon" />
  <div class="camera-box">
    <div class="camera">
      <video id="video" ref="video" @canplay="playVideo"></video>
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
    border-radius: 20px;
    transform: rotateY(180deg);
    -webkit-transform: rotateY(180deg); /* Safari and Chrome */
    -moz-transform: rotateY(180deg); /* Firefox */
  }
}
</style>
