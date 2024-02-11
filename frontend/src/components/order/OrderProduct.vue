<script setup>
defineProps({ thumb: String, option: Object, title: String })
</script>

<template>
  <div class="card">
    <img :src="thumb" alt="card-img" />
    <div class="info">
      <span class="title">{{ title }}</span>
      <span class="option">{{ option.optionName }} / {{ option.quantity }}개</span>
      <div class="price-group">
        <span class="price">{{ option.price.toLocaleString('kr-KR') }}원</span>
        <span class="discountPrice">{{ option.discountPrice.toLocaleString('kr-KR') }}원</span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.card {
  @include flex-box(flex-start, flex-start, row);
  @include box(90%, 100%, $light-gray, 10px, 0, 0.5rem);
  @include drop-shadow();
  gap: 1rem;

  img {
    width: 180px;
    height: 95px;
    object-fit: cover;
  }

  .info {
    .title {
      @include font-factory($fs-2, bold);
      display: block;
    }

    .option {
      @include font-factory($fs-1, normal);
    }

    .price-group {
      @include flex-box(flex-end, flex-start);
      gap: 0.5rem;

      .price {
        @include font-factory($fs-1, normal, $light-dark);
        text-decoration-line: line-through;
      }

      .discountPrice {
        @include font-factory($fs-2, bold, $main);
      }
    }
  }
}
</style>
