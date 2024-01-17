<script setup>
const ChatList = [{ user: '김빨강', content: '그래서 기능이 먼데;' }]
</script>

<template>
  <div class="live-chat"></div>
</template>

<style lang="scss" scoped>
.live-chat {
  @include box(100%, 100%, none, 20px, 1rem 0 1rem 0, 0.5rem 1rem);
  border: 3px solid $main;
}
</style>
