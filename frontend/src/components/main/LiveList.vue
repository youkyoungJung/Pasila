<script setup>
import { ref, watchEffect, watch, onMounted } from 'vue'
import { getPopularLiveApi, getVideosApi } from '@/components/api/SummaryAPI'
import VideoCard from '@/components/common/VideoCard.vue'
import ToggleButton from '@/components/common/ToggleButton.vue'
import router from '@/router'

const latestShortping = ref([])
const popularShortping = ref([])
const latestLive = ref([])
const popularLive = ref([])
const top5Shortping = ref([])

const props = defineProps(['categoryIndex'])
const selected = ref('popular')
const isLive = ref(true)
const categoryIndex = ref(props.categoryIndex)
const videos = ref([])

onMounted(() => {
  getDatas()
})

const getDatas = async () => {
  const res = await getVideosApi(0)
  top5Shortping.value = res.top5Shortping
  latestShortping.value = res.latestShortping
  popularShortping.value = res.popularShortping
  const resp = await getPopularLiveApi(0)
  latestLive.value = resp.latestLives
  popularLive.value = resp.popularLives

  videos.value = popularLive.value
}

//라이브, 쇼핑 토글
watch(isLive, () => {
  videoValue()
})

//카테고리 변경시
watchEffect(async () => {
  categoryIndex.value = props.categoryIndex
  if (isLive.value) {
    const resp = await getPopularLiveApi(categoryIndex.value)
    latestLive.value = resp.latestLives
    popularLive.value = resp.popularLives
  } else {
    const res = await getVideosApi(categoryIndex.value)
    latestShortping.value = res.latestShortping
    popularShortping.value = res.popularShortping
  }
  videoValue()
})

//최신순, 인기순
const toggle = async (e) => {
  selected.value = e.target.value
  videoValue()
}

const videoValue = () => {
  if (!isLive.value && selected.value == 'popular') videos.value = popularShortping.value
  else if (!isLive.value && selected.value == 'new') videos.value = latestShortping.value
  else if (isLive.value && selected.value == 'popular') videos.value = popularLive.value
  else if (isLive.value && selected.value == 'new') videos.value = latestLive.value
}

const goVideo = (video) => {
  if (video.on) {
    router.push(`/live/${video.liveId}`)
  } else {
    router.push(`/replay/${video.liveId}`)
  }
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
        <toggle-button
          :isLive="isLive"
          @clickShortping="() => (isLive = false)"
          @clickLive="() => (isLive = true)"
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
          <video-card :video="video" @click="goVideo(video)" />
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
    @include flex-box($align: flex-start, $direction: column);

    .title {
      @include font-factory($fs-4, bold);
      .subtitle {
        @include font-factory($fs-1, 400);
        margin-left: 5px;
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
      @include flex-box(flex-start, space-between);
      overflow: hidden;
      flex-wrap: wrap;
    }
  }
}
</style>
