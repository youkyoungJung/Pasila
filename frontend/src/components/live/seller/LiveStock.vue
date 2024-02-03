<script setup>
const ProductOptions = [
  { name: '구성1', stock: 13, total: 40 },
  { name: '구성2', stock: 14, total: 40 },
  { name: '구성1', stock: 40, total: 40 }
]
</script>

<template>
  <div class="stock-box">
    <div class="row" v-for="(item, index) in ProductOptions" :key="index">
      <span class="name">{{ item.name }}</span>
      <div class="stock-total">
        <span class="badge stock">{{ item.stock }}</span>
        /
        <span class="badge total">{{ item.total }}</span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.stock-box {
  flex: 1;
  @include box(null, 26vh, none, 20px, 0, 0.5rem 1rem);
  border: 3px solid $main;
  overflow-y: auto;
  .row {
    @include flex-box(center, space-between);
    @include box(100%, fit-content, none, none, none, 0.8rem 0);
    .name {
      @include font-factory($fs-3, bold);
    }
    .stock-total {
      height: fit-content;
      .badge {
        @include box(3rem, 2rem, none, 1.8rem, 0, 0.4rem);
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
