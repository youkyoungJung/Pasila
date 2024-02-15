<script setup>
import router from '@/router'
import { useLiveendStore } from '@/stores/liveend'

const { liveendInfo } = useLiveendStore()
</script>

<template>
  <div class="live-end">
    <img src="@/assets/img/disable-live-badge.png" alt="live-end" />
    <h1>라이브가 종료되었습니다.</h1>
    <div class="report-box">
      <p>라이브 진행 시간: {{ liveendInfo.totalBroadcastTime }}</p>
      <p>좋아요 수: {{ liveendInfo.likeCnt }}개</p>
      <p>시청자 수: {{ liveendInfo.participant }}명</p>
    </div>
    <button @click="() => router.push('/')" class="home">홈 화면</button>
    <button @click="() => router.push(`/channel/${member.id}`)" class="channel">내 채널</button>
  </div>
</template>

<style lang="scss" scoped>
.live-end {
  @include flex-box(center, center, column);
  gap: 1rem;
  @include box(null, 100%, none, 0, 0, 3rem);

  .report-box {
    @include flex-box(center, center, column);
    @include box(50%, 100%, none, 20px, 0, 0);
    border: 2px solid $gray;

    p {
      @include font-factory($fs-3, bold);
    }
  }

  .home {
    @include box(50%, 100%, $main, 0, 1rem 0 0 0, 1rem 0);
    @include font-factory($fs-1, normal, white);
    border: none;
    outline: none;
  }

  .channel {
    @include box(50%, 100%, white, 0, 0, 1rem 0);
    @include font-factory($fs-1, normal, $main);
    border: 2px solid $main;
    outline: none;
  }
}
</style>
