
<script setup>
import { ref } from 'vue';

//숏핑 받아오면 숏핑의 제품 id로 판매자 id가져와서 판매자 정보 뿌리기
const videos = ref([
    {
        
        src: new URL("@/assets/img/main-sample.png", import.meta.url).href,
        profile: new URL("@/assets/img/karina.jpg", import.meta.url).href,
        name: '카리나나',
        title: '겨울에 필수! 앙고라 니트',
        price: '36,000',
        discountPrice: '20,000',
        
        
    },
    {
        src: new URL("@/assets/img/main-sample2.png", import.meta.url).href,
        profile: new URL("@/assets/img/jenny.jpg", import.meta.url).href,
        name: '김제니',
        title: '제니도 쓴다는 그 스킨',
        price: '18,000',
        discountPrice: '15,000',
        
        
    },    {
        src: new URL("@/assets/img/main-sample3.png", import.meta.url).href,
        profile: new URL("@/assets/img/rose.jpg", import.meta.url).href,
        name: '로제제',
        title: '꿀보이스 만들어 주는 배 도라지 차',
        price: '36,000',
        discountPrice: '20,000',
        
        
    },
    {
        src: new URL("@/assets/img/main-sample.png", import.meta.url).href,
        profile: new URL("@/assets/img/jenny.jpg", import.meta.url).href,
        name: '김가을',
        title: '겨울에 필수! 앙고라 니트',
        price: '36,000',
        discountPrice: '20,000',
        
        
    },
])
</script>

<template>
    <div class="container">
        <div class="popular">
            <div class="title">인기 <span style="color: #ff495c;">숏</span>핑<span class="subtitle">지금 가장 인기있는 숏핑을 확인해보세요.</span></div>
            <div class="video-container">
                <div v-for="(video, i) in videos" :key="i" class="videos">
                    <div class="numbering">
                        <div>{{ i + 1 }}</div>
                        <div>
                            <!-- <video :src="video.src" class="input-video" /> -->
                            <div class="input-video" v-bind:style="{backgroundImage: `url(${video.src})`}"></div>
                            <div class="user-info">
                                <img :src="video.profile" class="user-profile">
                                <div class="user-name">
                                    <div>{{ video.name }}</div>
                                    <div>{{ video.title }}</div>
                                </div>
                                <div class="price">
                                    <div class="origin-price">{{ video.price }}</div>
                                    <div class="discount-price">{{ video.discountPrice }}</div>
                                </div>
                            </div>
                        </div>
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
                font-size: 13px;
                margin-left: 5px;
            }
        }
        
        .video-container {
            @include box(95%, 90%, null, 0, 5px, 10px);
            @include flex-box($justify: flex-start);
            overflow-x: auto;
            overflow-y: hidden;
            white-space: nowrap;

            .videos {
                @include box(500px, 400px, none, none, 5px, 5px);
                @include flex-box();
                .numbering {
                    @include flex-box($align: flex-start);
                    @include font-factory($fs-4, bold);
                
                }
                .input-video {
                    background-size: cover;
                    width: 400px;
                    height: 300px;
                    border-radius: 10px;
                    margin-left: 10px
                    
                    
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