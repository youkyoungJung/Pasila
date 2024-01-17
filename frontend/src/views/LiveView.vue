<script setup>
import LiveCamera from '@/components/live/LiveCamera.vue'
import LiveScript from '@/components/live/LiveScript.vue'
import LiveStock from '@/components/live/LiveStock.vue'
import LiveChat from '@/components/live/LiveChat.vue'
</script>

<template>
  <div class="content">
    <section class="col-1">
      <live-camera></live-camera>
      <live-script></live-script>
    </section>
    <section class="col-2">
      <live-stock></live-stock>
      <live-chat></live-chat>
    </section>
    <section class="col-3"></section>
  </div>
</template>

<style lang="scss" scoped>
.content {
  @include flex-box(flex-start, space-between);
  padding: 2rem;
  .col-1 {
    width: 50%;
    padding: 0 1rem 0 0;
  }
  .col-2 {
    width: 25%;
  }
  .col-3 {
    width: 25%;
  }
}
</style>
