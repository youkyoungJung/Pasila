<script setup>
import { ref } from 'vue'

const categories = [
  //대분류 카테고리 이름들
  {
    img: new URL('@/assets/img/category-all.png', import.meta.url).href,
    name: '전체'
  },
  {
    img: new URL('@/assets/img/category-beauty.png', import.meta.url).href,
    name: '뷰티'
  },
  {
    img: new URL('@/assets/img/category-food.png', import.meta.url).href,
    name: '음식'
  },
  {
    img: new URL('@/assets/img/category-fashion.png', import.meta.url).href,
    name: '패션'
  },
  {
    img: new URL('@/assets/img/category-life.png', import.meta.url).href,
    name: '라이프'
  },
  {
    img: new URL('@/assets/img/category-travel.png', import.meta.url).href,
    name: '여행'
  },
  {
    img: new URL('@/assets/img/category-tech.png', import.meta.url).href,
    name: '테크'
  },
  {
    img: new URL('@/assets/img/category-kid.png', import.meta.url).href,
    name: '유아'
  },
  {
    img: new URL('@/assets/img/category-camp.png', import.meta.url).href,
    name: '레저'
  },
  {
    img: new URL('@/assets/img/category-ticket.png', import.meta.url).href,
    name: '티켓'
  }
]

const selectedBigCate = ref('전체')

const selectBigCate = (name) => {
  selectedBigCate.value = name
}
</script>

<template>
  <div class="container">
    <ul class="category">
      <div class="big-category">
        <li
          v-for="category in categories"
          :key="category"
          class="big-cate-li"
          @click="selectBigCate(category.name)"
        >
          <img :src="category.img" />
          <div :class="[category.name == selectedBigCate ? 'select-big-name' : 'type-big-name']">
            {{ category.name }}
          </div>
        </li>
      </div>
    </ul>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include flex-box();

  .category {
    @include box(100%, 30%, none, 0, 5px, 2px);
    @include flex-box($justify: space-evenly, $direction: column);
    margin-top: 10px;
    cursor: pointer;
    .select-big-name {
      @include box(90%, 100%, none, 0, 0, 0);
      @include font-factory(18px, 500);
      color: $main;
      margin-top: 5px;
    }
    .type-big-name {
      @include box(90%, 100%, none, 0, 0, 0);
      @include font-factory(15px, 500);
      margin-top: 5px;
    }

    .big-category {
      @include box(100%, 60%, none, 0, 5px, 2px);
      @include flex-box($justify: space-evenly);
      .big-cate-li {
        @include flex-box($direction: column);
        text-align: center;

        img {
          @include box(50px, 50px, none, 0, 5px, 2px);
        }
      }
    }
  }
}
</style>
