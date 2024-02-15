<script setup>
defineProps({ order: Object })
</script>

<template>
  <div class="container">
    <div class="date">{{ order.createdAt.substring(0, 10) }}</div>
    <div>
      <b>수령자</b> {{ order.buyerName }} <br />
      <b>배송지</b> {{ order.address }}
    </div>
    <div class="card">
      <img :src="order.productUrl" alt="card-img" />
      <div class="info">
        <span class="title">{{ order.productName }}</span>
        <span class="option">{{ order.productOptionName }} / {{ order.orderCnt }}개</span>
        <div class="price-group">
          <span class="discountPrice">{{ order.price.toLocaleString('kr-KR') }}원</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include flex-box(flex-start, flex-start, column);
  width: 80%;
  gap: 1rem;

  .date {
    @include font-factory($fs-2, bold, $orange);
  }
}
.card {
  @include flex-box(flex-start, flex-start, row);
  @include box(100%, 100%, $light-gray, 10px, 0, 0.5rem);
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
