<script setup>
import ShortpingTitle from '@/components/shortping/ShortpingTitle.vue'
import ShortpingVideo from '@/components/shortping/ShortpingVideo.vue'
import ShortpingTimeline from '@/components/shortping/ShortpingTimeline.vue'
import ShortpingHighlight from '@/components/shortping/ShortpingHighlight.vue'
</script>

<template>
  <div class="container">
    <div class="short-title">
      <span style="color: #ff495c">숏핑 제작</span>
      <span class="short-subtitle"
        >AI가 선택한 하이라이트를 확인하고 편집해 나만의 숏핑을 만들어보세요!</span
      >
    </div>
    <div class="shortping-body">
      <div class="show-video">
        <shortping-title />
        <shortping-video />
        <shortping-timeline />
      </div>
      <div class="show-highlight">
        <shortping-highlight />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include font-factory(null, null, null);
  padding: 2rem;
  .short-title {
    @include font-factory($fs-4, bold);

    .short-subtitle {
      @include font-factory($fs-1, 400);
      margin-left: 5px;
    }
  }

  .shortping-body {
    @include box(100%, 100%, blue, 0, 0, 0);
    display: flex;
    align-items: flex-start;
    justify-content: space-around;
    .show-video {
      @include box(58%, 100%, red, 0, 0, 0);
      display: flex;
      flex-direction: column;
    }

    .show-highlight {
      @include box(38%, 100%, pink, 0, 0, 0);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: flex-start;
    }
  }
}
</style>
