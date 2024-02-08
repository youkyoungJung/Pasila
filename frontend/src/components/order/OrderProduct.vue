<script setup>
defineProps({ product: Object })
</script>

<template>
  <div class="card">
    <img src="https://cdn.imweb.me/thumbnail/20230208/700655507eefc.jpg" alt="card-img" />
    <span></span>
  </div>
</template>

<style lang="scss" scoped>
.card {
  @include box(80%, 100%, $light-gray, 10px, 0, 0.5rem);
  @include drop-shadow();
}
</style>
