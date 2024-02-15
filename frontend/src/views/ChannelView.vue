<script setup>
import { ref, onMounted } from 'vue'
import {
  getChannelApi,
  getChannelLiveApi,
  getChannelShortpingApi
} from '@/components/api/MemberAPI'
import router from '@/router'
import { deleteShortpingApi } from '@/components/api/ShortpingAPI'
import { deleteLiveApi } from '@/components/api/OpenviduAPI'
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
    <div class="video-container">
      <template v-if="isLive">
        <video-card
          v-for="(item, index) in liveList"
          :key="index"
          :video="item.live"
          :is-my-channel="isMyChannel"
          @click-video="() => console.log('click video card')"
        >
          <template #btn>
            <button
              class="video-btn"
              v-if="item.status === 'RESERVED'"
              @click.stop="() => router.push(`/live/${item.live.liveId}`)"
            >
              라이브 시작
            </button>
            <button
              class="video-btn"
              v-if="item.status === 'NOT_SHORTPING'"
              @click.stop="() => router.push(`/edit/${item.live.liveId}`)"
            >
              숏핑 제작
            </button>
            <button
              class="video-btn"
              @click.stop="
                async () => {
                  await deleteLiveApi(item.live.liveId)
                  router.go()
                }
              "
            >
              삭제
            </button>
          </template>
        </video-card>
      </template>
      <template v-else>
        <video-card
          v-for="(item, index) in shortpingList"
          :key="index"
          :video="item"
          :is-my-channel="isMyChannel"
        >
          <template #btn>
            <button
              class="video-btn"
              @click.stop="
                async () => {
                  await deleteShortpingApi(item.id)
                  router.go()
                }
              "
            >
              삭제
            </button>
          </template>
        </video-card>
      </template>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.channel {
  padding: 2rem 3rem;
}

.video-container {
  @include box(100%, 90%, null, 0, 5px, 0);
  @include flex-box(flex-start, center);
  overflow: hidden;
  flex-wrap: wrap;

  .video-btn {
    @include box(null, null, none, 10px, 0, 0.3rem 1rem);
    background: none;
    @include font-factory($fs-1, bold, white);
    border: 1px solid white;
    cursor: pointer;

    &:hover {
      background-color: white;
      color: $dark;
    }
  }
}
</style>
