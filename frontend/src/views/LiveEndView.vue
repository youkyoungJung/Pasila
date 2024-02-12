<script setup>
import router from '@/router'
</script>

<template>
  <div class="live-end">
    <img src="@/assets/img/disable-live-badge.png" alt="live-end" />
    <h1>라이브가 종료되었습니다.</h1>
    <div class="report-box">
      <p>라이브 진행 시간: 1:23:11</p>
      <p>좋아요 수: 1,300개</p>
      <p>시청자 수: 230명</p>
    </div>
    <button @click="() => router.push('/')">홈 화면</button>
    <!-- TODO: 채널 아이디 변수로 변경필요 -->
    <button @click="() => router.push('/channel/1')">내 채널</button>
  </div>
</template>

<style lang="scss" scoped>
.live-end {
  @include flex-box(center, center, column);
  @include box(null, 100%, none, 0, 0, 3rem);

  .report-box {
    @include flex-box(center, center, column);
    @include box(50%, 100%, none, 20px, 0, 2rem);
    border: 2px solid $light-dark;

    p {
      @include font-factory($fs-3, bold);
    }
  }
}
</style>
