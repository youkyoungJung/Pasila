<script setup>
import { ref, onMounted } from 'vue'
import VideoCard from '@/components/common/VideoCard.vue'

const props = defineProps(['data'])

onMounted(() => {
  getDatas()
})

const getDatas = async () => {
  videos.value = props.data
  console.log(videos.value)
}
const videos = ref([
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/karina.jpg', import.meta.url).href,
    channel: '카리나나',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample2.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/jenny.jpg', import.meta.url).href,
    channel: '김제니',
    title: '제니도 쓴다는 그 스킨',
    price: '18,000',
    discountPrice: '15,000'
  },
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample3.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/rose.jpg', import.meta.url).href,
    channel: '로제제',
    title: '꿀보이스 만들어 주는 배도라지 차',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/jenny.jpg', import.meta.url).href,
    channel: '김가을',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  }
])
</script>

<template>
  <div class="container">
    <div class="popular">
      <div class="title">
        인기
        <span style="color: #ff495c">숏</span>핑
        <span class="subtitle">지금 가장 인기있는 숏핑을 확인해보세요.</span>
      </div>
      <div class="video-container">
        <div v-for="(video, i) in videos" :key="i">
          <div class="numbering">
            <div>{{ i + 1 }}</div>
            <video-card :video="video" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(100%, 90%, none, 0, 5px, 2px);
  @include flex-box();
  .popular {
    @include box(90%, 500px, null, 0, 5px, 5px);
    @include flex-box($align: flex-start, $direction: column);

    .title {
      @include font-factory($fs-4, bold);

      .subtitle {
        @include font-factory($fs-1, 400);
        margin-left: 5px;
      }
    }

    .video-container {
      @include box(95%, 90%, null, 0, 5px, 10px);
      @include flex-box($align: flex-start, $justify: flex-start);
      overflow-x: auto;
      overflow-y: hidden;
      white-space: nowrap;
      .numbering {
        @include flex-box($align: flex-start);
        @include font-factory($fs-4, bold);
      }
    }
  }
}
</style>
