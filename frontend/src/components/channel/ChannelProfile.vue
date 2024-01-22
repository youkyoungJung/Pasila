<script setup>
defineProps({
  member: Object
})
</script>

<template>
  <div class="channel-profile">
    <div class="profile-img" :style="{ backgroundImage: `url(${member?.profile})` }"></div>
    <div class="profile-desc">
      <span class="name">{{ member?.name }}</span>
      <span class="desc">{{ member?.desc }}</span>
      <button class="order">주문관리</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.channel-profile {
  @include flex-box(center, flex-start);
  .profile-img {
    @include box(10rem, 10rem, $gray, 50%, 0 2rem 0 0, 0);
  }
  .name {
    @include font-factory($fs-4, bold);
  }
  .desc {
    @include font-factory($fs-3, 500);
    margin: 1rem 0;
  }
  .name,
  .desc {
    display: block;
    white-space: pre-line;
  }

  .order {
    @include box(fit-content, fit-content, $gray, 10px, 0, 0.3rem 2rem);
    @include font-factory($fs-1, bold);
    border: none;
  }
}
</style>
