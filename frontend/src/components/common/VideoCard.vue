<script setup>
import { ref } from 'vue'

defineProps({
  video: Object,
  isMyChannel: Boolean
})

let isHover = ref(false)
</script>

<template>
  <div class="video-card">
    <div
      class="input-video"
      :style="{ backgroundImage: `url(${video?.productThumbnailUrl})` }"
      @click="$emit('clickVideo')"
      @mouseover="() => (isHover = true)"
      @mouseleave="() => (isHover = false)"
    >
      <template v-if="isMyChannel">
        <div v-show="isHover" class="video-control">
          <slot name="btn">
            <!-- video 상태에 따른 버튼이 위치합니다. -->
          </slot>
        </div>
      </template>
    </div>
    <div class="user-info">
      <div
        :style="{ backgroundImage: `url(${video?.profileUrl}` }"
        class="user-profile"
        @click="$emit('clickChannel')"
      ></div>
      <div class="user-name">
        <div class="name">{{ video?.channel }}</div>
        <div class="title">{{ video?.title }}</div>
      </div>
      <div class="price">
        <div class="origin-price">{{ video?.price.toLocaleString('kr-KR') }}원</div>
        <div class="discount-price">{{ video?.discountPrice.toLocaleString('kr-KR') }}원</div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.video-card:hover .video-control {
  display: block;
}
.video-card {
  margin: 0 1rem;
  padding: 1rem 2rem;
  cursor: pointer;
  .input-video {
    background-size: cover;
    background-position: center;
    width: 400px;
    height: 300px;
    border-radius: 10px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    margin-bottom: 0.5rem;

    position: relative;

    .video-control {
      position: absolute;
      z-index: 1;
      top: 0;
      left: 0;

      width: 400px;
      height: 300px;
      border-radius: 10px;
      background-color: rgba(0, 0, 0, 0.473);

      @include flex-box(center, space-around);
    }
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
