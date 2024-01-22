<script setup>
import VBestShortPing from '../category/VBestShortPing.vue';
import VLive from '../category/VLive.vue';


const categories = [
    {
        img: new URL("@/assets/img/category-all.png", import.meta.url).href,
        name: "전체",
    },
    {
        img: new URL("@/assets/img/category-beauty.png", import.meta.url).href,
        name: "뷰티",
    },
    {
        img: new URL("@/assets/img/category-food.png", import.meta.url).href,
        name: "음식",
    },
    {
        img: new URL("@/assets/img/category-fashion.png", import.meta.url).href,
        name: "패션",
    },
    {
        img: new URL("@/assets/img/category-life.png", import.meta.url).href,
        name: "라이프",
    },
    {
        img: new URL("@/assets/img/category-travel.png", import.meta.url).href,
        name: "여행",
    },
    {
        img: new URL("@/assets/img/category-tech.png", import.meta.url).href,
        name: "테크",
    },
    {
        img: new URL("@/assets/img/category-kid.png", import.meta.url).href,
        name: "유아",
    },
    {
        img: new URL("@/assets/img/category-camp.png", import.meta.url).href,
        name: "레저",
    },
    {
        img: new URL("@/assets/img/category-ticket.png", import.meta.url).href,
        name: "티켓",
    },
];


</script>

<template>
    <div class="container">
        <ul class="category">
            <li v-for="category in categories" :key="category" class="category-type">
                <img :src="category.img">
                <div class="type-name">{{ category.name }}</div>
            </li>
            <!-- 이곳에 중분류, 그 아래 최종분류 v-for로 넣어서
            선택된거에 따라 아래에 
            VBestShortPing, VLive보이게 하기!!!! -->
        </ul>
    </div>
    <VBestShortPing />
    <VLive />
</template>



<style lang="scss" scoped>
.container {
    @include flex-box();
   
    .category {
        @include box(90%, 80px, none, 0, 5px, 2px);
        @include flex-box($justify: space-evenly);
        margin-top: 10px;

        .category-type {
            text-align: center;
            @include flex-box($direction:column);
            @include font-factory(13px, 500);


            img {
            @include box(50px, 50px, none, 0, 5px, 2px);
            }
            .type-name {
                margin-top: 5px;
            }
        }

    }
}



</style>