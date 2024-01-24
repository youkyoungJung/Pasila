<script setup>
defineProps({
  video: Object
})
</script>

<template>
  <div class="video-card">
    <!-- <video :src="video.src" class="input-video" /> -->
    <div class="input-video" :style="{ backgroundImage: `url(${video?.src})` }"></div>
    <div class="user-info">
      <div :style="{ backgroundImage: `url(${video?.profile}` }" class="user-profile"></div>
      <div class="user-name">
        <div class="name">{{ video?.name }}</div>
        <div class="title">{{ video?.title }}</div>
      </div>
      <div class="price">
        <div class="origin-price">{{ video?.price }}원</div>
        <div class="discount-price">{{ video?.discountPrice }}원</div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.video-card {
  margin: 0 1rem;
  padding: 1rem 2rem;
  .input-video {
    background-size: cover;
    background-position: center;
    width: 400px;
    height: 300px;
    border-radius: 10px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    margin-bottom: 0.5rem;
  }
  .user-info {
    @include flex-box($align: flex-start, $justify: space-evenly);
    width: 400px;
    .user-profile {
      @include box(50px, 50px, null, 50%, 5px, 2px);
      background-size: cover;
      background-position: center;
    }
    .user-name {
      @include box(60%, 100%, null, 0, 5px, 2px);
      .name {
        @include font-factory($fs-1, 500);
      }
      .title {
        @include font-factory($fs-2, bold);
        white-space: pre-line;
        word-break: keep-all;
      }
    }
    .price {
      @include box(null, 100%, null, 0, 5px, 2px);
      text-align: right;
      min-width: fit-content;

      .origin-price {
        @include font-factory($fs-1, 400, $light-dark);
        text-decoration: line-through;
      }

      .discount-price {
        @include font-factory(23px, bold, $main);
      }
    }
  }
}
</style>
