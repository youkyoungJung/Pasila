<script setup>
import { ref, onMounted } from 'vue'
import VideoCard from '@/components/common/VideoCard.vue'
import { getVideosApi } from '@/components/api/SummaryAPI'
import router from '@/router';

const videos = ref([])
const top5Shortping = ref([])
onMounted(() => {
  getDatas()
})

const getDatas = async () => {
  const res = await getVideosApi(0)
  top5Shortping.value = res.top5Shortping

  videos.value = top5Shortping.value
}

const goVideo = (id) => {
  router.push(`/shortping/${id}`)
}
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
            <video-card :video="video" @click="goVideo(video.id)"/>
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
