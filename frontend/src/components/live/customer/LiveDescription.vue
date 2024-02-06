<script setup>
defineProps({
  description: String,
  options: Array
})
</script>

<template>
  <div class="live-desc-box">
    <div class="live-desc" v-dompurify-html="description"></div>
    <div class="order">
      <select name="count" data-title="옵션 선택">
        <option value="0">옵션 선택</option>
        <template v-for="(item, index) in options" :key="index">
          <option :value="item.name">{{ item.name }}</option>
        </template>
      </select>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-desc-box {
  @include box(null, calc(78vh - 5px), none, 20px, 0, 0);
  border: 3px solid $main;
  position: relative;

  .live-desc {
    padding: 1rem;
    height: 63vh;
    overflow-y: scroll;

    -ms-overflow-style: none;
    &::-webkit-scrollbar {
      display: none;
    }
  }
}

.order {
  @include flex-box(center, space-between, row);
  @include box(calc(100% - 2rem), 10vh, white, 0 0 20px 20px, 0, 0 1rem);
  position: absolute;
  bottom: 0;

  select {
    width: 100%;
    height: 2rem;
  }
}
</style>
