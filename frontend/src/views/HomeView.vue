<script setup>
import VCategory from '@/components/common/VCategory.vue'
import BestShortping from '@/components/main/BestShortping.vue'
import LiveList from '@/components/main/LiveList.vue'
import { getVideos, getPopularLive } from '@/components/api/SearchAPI'
import { ref, watch, onMounted } from 'vue'
const latestShortping = ref([])
const popularShortping = ref([])
const top5Shortping = ref([])
const latestLive = ref([])
const popularLive = ref([])
const isLive = ref(true)
onMounted(() => {
  getDatas()
})

const getDatas = async () => {
  const res = await getVideos(0)
  latestShortping.value = res.latestShortping
  popularShortping.value = res.popularShortping
  top5Shortping.value = res.top5Shortping
  const resp = await getPopularLive(0)
  latestLive.value = resp.latestLives
  popularLive.value = resp.popularLives
  // console.log(latestLive.value)
  // console.log(popularLive.value)
  // console.log(latestShortping.value)
  // console.log(popularShortping.value)
  // console.log(top5Shortping.value)
}

const categoryIndex = ref('')
watch(categoryIndex, async () => {
  if (isLive.value) {
    const resp = await getPopularLive(categoryIndex.value)
    latestLive.value = resp.latestLives
    popularLive.value = resp.popularLives
    // console.log(latestLive.value)
    // console.log(popularLive.value)
  } else {
    const res = await getVideos(categoryIndex.value)
    latestShortping.value = res.latestShortping
    popularShortping.value = res.popularShortping
    // console.log(latestShortping.value)
    // console.log(popularShortping.value)
  }
})

watch(isLive, async () => {
  if (isLive.value) {
    const resp = await getPopularLive(categoryIndex.value)
    latestLive.value = resp.latestLives
    popularLive.value = resp.popularLives
    // console.log(latestLive.value)
    // console.log(popularLive.value)
  } else {
    const res = await getVideos(categoryIndex.value)
    latestShortping.value = res.latestShortping
    popularShortping.value = res.popularShortping
    // console.log(latestShortping.value)
    // console.log(popularShortping.value)
  }
})
</script>

<template>
  <v-category
    @categoryIndex="
      (e) => {
        categoryIndex = e
      }
    "
  />
  <best-shortping :data="popularShortping" />
  <live-list
    :popularLive="popularLive"
    :latestLive="latestLive"
    :popularShortping="popularShortping"
    :latestShortping="latestShortping"
    @isLive="(e) => (isLive = e)"
  />
</template>

<style lang="scss">
.title {
  @include font-factory($fs-5, bold);
}
</style>
