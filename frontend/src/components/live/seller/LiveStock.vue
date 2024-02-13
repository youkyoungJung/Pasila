<script setup>
const props = defineProps({
  productOptions: Array
})
</script>

<template>
  <div class="stock-box">
    <h1>실시간 재고 확인</h1>
    <h2>재고 상태를 확인해보세요.</h2>
    <div class="row" v-for="(item, index) in productOptions" :key="index">
      <span class="name">{{ item.name }}</span>
      <div class="stock-total">
        <span class="badge stock">{{ item.stock }}개</span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.stock-box {
  flex: 1;
  @include box(null, 26vh, none, 20px, 0, 1rem);
  border: 3px solid $main;
  h1 {
    @include font-factory($fs-2, bold);
    margin: 0;
  }
  h2 {
    @include font-factory($fs-1, 500);
    margin: 0;
  }
  .row {
    @include flex-box(center, space-between);
    @include box(100%, fit-content, none, none, none, 0.8rem 0);
    overflow-y: auto;
    .name {
      @include font-factory($fs-3, bold);
    }
    .stock-total {
      height: fit-content;
      .badge {
        @include box(3rem, 2rem, none, 1.8rem, 0, 0.4rem 1rem);
        @include font-factory($fs-3, bold);
      }
      .stock {
        background-color: $soft-pink;
      }
      .total {
        background-color: $gray;
      }
    }
  }
}
</style>
