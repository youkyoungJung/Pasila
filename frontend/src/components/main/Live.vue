<script setup>
import { ref } from 'vue'
import VideoCard from '@/components/common/VideoCard.vue'

//라이브, 숏핑 확인하고
//받아오면 정렬대로 뿌리기
const videos = ref([
  {
    src: new URL('@/assets/img/main-sample3.png', import.meta.url).href,
    profile: new URL('@/assets/img/rose.jpg', import.meta.url).href,
    name: '로제제',
    title: '꿀보이스 만들어 주는 배 도라지 차',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    src: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profile: new URL('@/assets/img/karina.jpg', import.meta.url).href,
    name: '카리나나',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    src: new URL('@/assets/img/main-sample2.png', import.meta.url).href,
    profile: new URL('@/assets/img/jenny.jpg', import.meta.url).href,
    name: '김제니',
    title: '제니도 쓴다는 그 스킨',
    price: '18,000',
    discountPrice: '15,000'
  },
  {
    src: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profile: new URL('@/assets/img/jenny.jpg', import.meta.url).href,
    name: '김가을',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    src: new URL('@/assets/img/main-sample3.png', import.meta.url).href,
    profile: new URL('@/assets/img/rose.jpg', import.meta.url).href,
    name: '로제제',
    title: '꿀보이스 만들어 주는 배 도라지 차',
    price: '36,000',
    discountPrice: '20,000'
  },
  {
    src: new URL('@/assets/img/main-sample.png', import.meta.url).href,
    profile: new URL('@/assets/img/karina.jpg', import.meta.url).href,
    name: '카리나나',
    title: '겨울에 필수! 앙고라 니트',
    price: '36,000',
    discountPrice: '20,000'
  }
])

const isLive = ref(true)

const goLive = () => {
  if (!isLive.value) isLive.value = !isLive.value
}

const goShortping = () => {
  if (isLive.value) isLive.value = !isLive.value
}
</script>

<template>
  <div class="container">
    <div class="running">
      <div class="title">
        둘러보기
        <span class="subtitle">원하는 상품을 찾아보세요!</span>
      </div>
      <div class="order-type">
        <div class="live-short-btn">
          <button :class="[isLive ? 'chosen-btn' : 'no-btn']" @click="goLive">라이브</button>
          <button :class="[isLive ? 'no-btn' : 'chosen-btn']" @click="goShortping">숏핑</button>
        </div>
        <div>
          <form action="#">
            <select name="orders" id="order" class="select-type">
              <option value="popular">인기순</option>
              <option value="new">최신순</option>
            </select>
          </form>
        </div>
      </div>
      <div class="video-container">
        <template v-for="(video, i) in videos" :key="i">
          <video-card :video="video"></video-card>
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
      .live-short-btn {
        margin-top: 5px;
        .chosen-btn {
          @include font-factory($fs-2, bold);
          @include box(150px, 100%, $main, 10px, 10px, 10px);
          border: 1px solid $main;
          color: white;
          cursor: pointer;
        }

        .no-btn {
          @include font-factory($fs-2, bold);
          @include box(150px, 100%, white, 10px, 10px, 10px);
          border: 1px solid $main;
          color: $main;
          cursor: pointer;
        }
      }

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

      .videos {
        @include flex-box();
        .input-video {
          background-size: cover;
          background-position: center;
          width: 400px;
          height: 300px;
          border-radius: 10px;
          margin-left: 10px;
        }
        .user-info {
          @include font-factory($fs-1, null);
          @include flex-box($justify: space-evenly);

          .user-profile {
            @include box(50px, 50px, null, 50px, 5px, 2px);
            align-items: flex-start;
          }
          .user-name {
            @include box(60%, 100%, null, 0, 5px, 2px);
          }
          .price {
            @include box(20%, 100%, null, 0, 5px, 2px);
            text-align: right;

            .origin-price {
              @include font-factory(11px, null);
              text-decoration: line-through;
            }

            .discount-price {
              @include font-factory($fs-1, bold, $main);
            }
          }
        }
      }
    }
  }
}
</style>
