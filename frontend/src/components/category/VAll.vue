<template>
    <div class="container">
        <div class="popular">
            <div class="title">인기 <span style="color: #ff496c;">숏</span>핑<span style="font-size: 13px; margin-left: 5px;">지금 가장 인기있는 숏핑을 확인해보세요.</span></div>
            <div class="video-container">
                <div class="videos">
                    <!-- <video /> -->
                    인기 숏핑 넣기
                </div>
                <div class="videos">
                    <!-- <video /> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
.container {
    @include flex-box();
}
.popular {

    width: 90%;
    height: 500px;
    margin: 20px 10px;

    .title {
        @include font-factory($fs-4, bold);
    }

    .video-container {
        @include flex-box();
        .videos {
            @include box(500px, 300px, purple, none, 20px, 5px);
        }
    }
}
</style>