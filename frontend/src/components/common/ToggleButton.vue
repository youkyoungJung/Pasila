<script setup>
defineProps({
  isLive: Boolean
})
</script>

<template>
  <div class="live-short-btn">
    <button :class="[isLive ? 'chosen-btn' : 'no-btn']" @click="$emit('clickLive')">라이브</button>
    <button :class="[isLive ? 'no-btn' : 'chosen-btn']" @click="$emit('clickShortping')">
      숏핑
    </button>
  </div>
</template>

<style lang="scss" scoped>
.live-short-btn {
  margin: 1rem 0;
  .chosen-btn {
    @include font-factory($fs-2, bold);
    @include box(150px, 100%, $main, 10px, 10px, 10px);
    border: 1px solid $main;
    color: white;
    cursor: pointer;
  }

  .no-btn {
    @include font-factory($fs-2, bold);
    @include box(150px, 100%, white, 10px, 10px, 10px);
    border: 1px solid $main;
    color: $main;
    cursor: pointer;
  }
}
</style>
