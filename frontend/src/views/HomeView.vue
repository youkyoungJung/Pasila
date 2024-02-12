<script setup>
import VCategory from '@/components/common/VCategory.vue'
import BestShortping from '@/components/main/BestShortping.vue'
import LiveList from '@/components/main/LiveList.vue'
import { getVideos, getPopularLive } from '@/components/api/SearchAPI'
import { ref, watch, onMounted } from 'vue'
const latestShortping = ref([])
const popularShortping = ref([])
const top5Shortping = ref([])

onMounted(() => {
  getDatas()
})

const getDatas = async () => {
  const res = await getVideos(0)
  latestShortping.value = res.latestShortping
  popularShortping.value = res.popularShortping
  top5Shortping.value = res.top5Shortping
  const resp = await getPopularLive(0)
  console.log(resp)
}

const categoryIndex = ref('')
watch(categoryIndex, async () => {
  const res = await getVideos(categoryIndex.value)
  latestShortping.value = res.latestShortping
  popularShortping.value = res.popularShortping
  top5Shortping.value = res.top5Shortping
  console.log(latestShortping.value)
  console.log(popularShortping.value)
  console.log(top5Shortping.value)
  //이 데이터를 넘겨줄거야 어디에? best shortping과 livelist에!
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
  <live-list :popular="popularShortping" :latest="latestShortping" />
</template>

<style lang="scss">
.title {
  @include font-factory($fs-5, bold);
}
</style>
