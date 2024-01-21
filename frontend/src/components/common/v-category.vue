<script setup>
import VBestShortping from '@/components/main/v-best-shortping.vue';
import VLive from '@/components/main/v-live.vue';
import { ref } from 'vue';


const categories = [
    //대분류 카테고리 이름들
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

const midCategories = ref([
    //중분류 카테고리 이름들
    {
        name: "전체",
    },
    {
        name: "스킨케어",
    },
    {
        name: "헤어케어",
    },
    {
        name: "등등",
    },
    {
        name: "하암",
    },
    {
        name: "aa",
    },
    {
        name: "bb",
    },
    {
        name: "등cc",
    },
]);

const smallCategories = ref([
    //소분류 카테고리 이름들
    {
        name: "전체",
    },
    {
        name: "에센스",
    },
    {
        name: "크림",
    },
    {
        name: "아오",
    },
    {
        name: "22",
    },
    {
        name: "344",
    },
    {
        name: "이것",
    },
    {
        name: "저것",
    },
    {
        name: "야호",
    },
])
const selectedBigCate = ref('');
const selectedMidCate = ref('');
const selectedSmallCate = ref('');

const selectBigCate = (name) => {
    selectedBigCate.value = name; 
   
    if (selectedBigCate.value == '전체') {
        selectedMidCate.value = '';
        selectedSmallCate.value = '';
    }

    
}

const selectMidCate = (name) => {
    selectedMidCate.value = name;
    
}

const selectSmallCate = (name) => {
    selectedSmallCate.value = name;
}

</script>

<template>
    <div class="container">
        <ul class="category">
            <div class="big-category">
                <li v-for="category in categories" :key="category" class="big-cate-li" @click="selectBigCate(category.name)">
                    <img :src="category.img">
                    <div class="select-big-name" v-if="category.name == selectedBigCate" >{{ category.name }}</div>
                    <div class="type-big-name" v-else>{{ category.name }}</div>
                </li>
            </div>
            <div class="mid-category" v-if="selectedBigCate != '' && selectedBigCate != '전체'">
                <li v-for="midCategory in midCategories" :key="midCategory" class="mid-cate-li" @click="selectMidCate(midCategory.name)">
                    <div class="select-name" v-if="midCategory.name == selectedMidCate">{{ midCategory.name }}</div>
                    <div class="type-name" v-else>{{ midCategory.name }}</div>
                </li>
            </div>
            <div class="small-category" v-if="selectedMidCate != '' && selectedMidCate != '전체'">
                <li v-for="smallCategory in smallCategories" :key="smallCategory" class="small-cate-li" @click="selectSmallCate(smallCategory.name)">
                    <div class="select-name" v-if="smallCategory.name == selectedSmallCate">{{ smallCategory.name }}</div>
                    <div class="type-name" v-else>{{ smallCategory.name }}</div>
                </li>
            </div>
        </ul>

        
    </div>
    <v-best-shortping />
    <v-live />
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

        .select-name {
            @include box(90%, 100%, none, 0, 0, 0);
            @include font-factory(13px, 500);
            color: $main;
            border-bottom: 1px solid $main;
            margin-top: 5px;
            
        }
        .type-name {
            @include box(90%, 100%, none, 0, 0, 0);
            @include font-factory(13px, 500);
            margin-top: 5px;
            border-bottom: 1px dashed $dark;
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

        .mid-category {
            @include box(90%, 20%, whitesmoke, 0, 5px, 2px);
            @include flex-box($align: left, $direction: row);

            .mid-cate-li {
                @include flex-box($justify: flex-start);
                @include box(90%, 100%, none, 0, 2px, 3px);
                // border-bottom: 1px solid $dark;
                text-align: center;
                
            }
        }

        .small-category {
            @include box(90%, 20%, none, 5px, 5px, 2px);
            @include flex-box($align: left, $direction: row);
            .small-cate-li {
                @include flex-box($justify: flex-start);
                @include box(90%, 100%, none, 0, 2px, 3px);
                text-align: center;
            }
        }
    }
}



</style>