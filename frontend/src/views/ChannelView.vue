<script setup>
import { ref, onMounted } from 'vue'
import {
  getChannelApi,
  getChannelLiveApi,
  getChannelShortpingApi
} from '@/components/api/MemberAPI'
import ChannelProfile from '@/components/channel/ChannelProfile.vue'
import ToggleButton from '@/components/common/ToggleButton.vue'
import VideoCard from '@/components/common/VideoCard.vue'

const props = defineProps(['channelId'])

let isLive = ref(true)
let member = ref(null)
let isMyChannel = ref(false)
const liveList = ref(null)
const shortpingList = ref(null)

onMounted(async () => {
  const data = await getChannelApi(props.channelId)
  member.value = data
  if (localStorage.getItem('id') == props.channelId) isMyChannel.value = true

  liveList.value = await getChannelLiveApi(props.channelId)
  shortpingList.value = await getChannelShortpingApi(props.channelId)
})

const goLive = () => {
  if (!isLive.value) isLive.value = !isLive.value
}

const goShortping = () => {
  if (isLive.value) isLive.value = !isLive.value
}
</script>

<template>
  <div class="channel">
    <channel-profile
      v-if="member"
      :member="member"
      :channel-id="channelId"
      :is-my-channel="isMyChannel"
    />
    <toggle-button :is-live="isLive" @click-live="goLive" @click-shortping="goShortping" />
    <template v-if="isLive">
      <video-card v-for="(item, index) in liveList" :key="index" :video="item.live" />
    </template>
    <template v-else>
      <video-card v-for="(item, index) in shortpingList" :key="index" :video="item" />
      <video-card v-for="(item, index) in shortpingList" :key="index" :video="item" />
      <video-card v-for="(item, index) in shortpingList" :key="index" :video="item" />
      <video-card v-for="(item, index) in shortpingList" :key="index" :video="item" />
      <video-card v-for="(item, index) in shortpingList" :key="index" :video="item" />
      <video-card v-for="(item, index) in shortpingList" :key="index" :video="item" />
    </template>
  </div>
</template>

<style lang="scss" scoped>
.channel {
  padding: 2rem 3rem;
}
</style>
