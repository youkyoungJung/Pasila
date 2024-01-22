<script setup>
defineProps({
  member: Object
})
</script>

<template>
  <div class="channel-card">
    <div
      class="profile"
      :style="{ background: `center / cover url(${member?.profile}) no-repeat` }"
    ></div>
    <div class="name">{{ member?.member }}</div>
  </div>
</template>

<style lang="scss" scoped>
.channel-card {
  @include flex-box(center, center, column);
  width: 25%;
  .profile {
    @include box(5rem, 5rem, null, 50px, null, null);
  }
  .name {
    @include font-factory($fs-2, bold);
  }
}
</style>
