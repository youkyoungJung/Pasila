<script setup></script>

<template>
  <span class="search-box">
    <input type="text" placeholder="채널명 또는 라이브 제목" />
    <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
  </span>
</template>

<style lang="scss" scoped>
.search-box {
  @include box(20rem, 2rem, $soft-pink, 2rem, 0, 0.3rem 1rem);
  @include flex-box(center, space-between);
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25) inset;
  input {
    @include font-factory($fs-1, 400, $light-dark);
    background: none;
    border: none;
    outline: none;
  }
}
</style>
