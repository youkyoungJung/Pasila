<script setup>
import { ref, watch } from 'vue'
import VideoCard from '@/components/common/VideoCard.vue'
import ToggleButton from '@/components/common/ToggleButton.vue'

const props = defineProps(['popularLive', 'latestLive', 'popularShortping', 'latestShortping'])
const selected = ref('popular')
const isLive = ref(true)

watch(isLive, () => {
  // if (!isLive && selected.value == 'popular') videos.value = props.popularShortping
  // else if (!isLive && selected.value == 'new') videos.value = props.latestShortping
  // else if (isLive && selected.value == 'popular') videos.value = props.popularLive
  // else if (isLive && selected.value == 'new') videos.value = props.latestLive
})
const toggle = async (e) => {
  selected.value = e.target.value
  // if (!isLive && selected.value == 'popular') videos.value = props.popularShortping
  // else if (!isLive && selected.value == 'new') videos.value = props.latestShortping
  // else if (isLive && selected.value == 'popular') videos.value = props.popularLive
  // else if (isLive && selected.value == 'new') videos.value = props.latestLive
}
const videos = ref([
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample3.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/rose.jpg', import.meta.url).href,
    channel: '로제제',
    title: '꿀보이스 만들어 주는 배 도라지 차',
    price: '36,000',
    discountPrice: '20,000'
  },
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
    productThumbnailUrl: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/jenny.jpg', import.meta.url).href,
    channel: '김가을',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample3.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/rose.jpg', import.meta.url).href,
    channel: '로제제',
    title: '꿀보이스 만들어 주는 배 도라지 차',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    productThumbnailUrl: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profileUrl: new URL('@/assets/img/karina.jpg', import.meta.url).href,
    channel: '카리나나',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  }
])
</script>

<template>
  <div class="container">
    <div class="running">
      <div class="title">
        둘러보기
        <span class="subtitle">원하는 상품을 찾아보세요!</span>
      </div>
      <div class="order-type">
        <toggle-button
          @isLive="
            (e) => {
              isLive = e
              $emit('isLive', e)
            }
          "
        />
        <div>
          <form action="#">
            <select name="orders" id="order" class="select-type" @change="toggle">
              <option value="popular" selected>인기순</option>
              <option value="new">최신순</option>
            </select>
          </form>
        </div>
      </div>
      <div class="video-container">
        <template v-for="(video, i) in videos" :key="i">
          <video-card :video="video" />
        </template>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(100%, 100%, none, 0, 5px, 2px);
  @include flex-box();

  .running {
    @include box(90%, 100%, null, 0, 5px, 5px);
    @include flex-box($align: center, $direction: column);

    .title {
      @include font-factory($fs-4, bold);
      @include box(100%, null, null, 0, 0, 0);
      @include flex-box($align: flex-end, $justify: flex-start);
      .subtitle {
        display: inline-block;
        font-size: 13px;
        margin-left: 5px;
        line-height: 28px;
      }
    }

    .order-type {
      @include flex-box($justify: space-between);
      @include box(100%, 10%, null, 0, 2px, 5px);

      .select-type {
        @include font-factory($fs-2, null);
        -webkit-appearance: none; /* 네이티브 외형 감추기 */
        -moz-appearance: none;
        appearance: none;
        @include box(150px, 100%, $main, 10px, 10px, 10px);
        cursor: pointer;
        color: white;
        text-align: center;
        border: 1px solid $main;
        outline: none;

        option {
          background-color: white;
          color: $dark;
          padding: 3px 0;
          border-radius: 10px;
        }
      }
    }

    .video-container {
      @include box(100%, 90%, null, 0, 5px, 10px);
      @include flex-box(flex-start);
      overflow: hidden;
      flex-wrap: wrap;
    }
  }
}
</style>
