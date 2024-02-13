<script setup></script>

<template>
  <div class="container">
    <img src="@/assets/img/box-img.png" alt="box-img" />
    <h2>주문이 완료되었습니다!</h2>
    <h3>상품을 주문해주셔서 감사합니다.</h3>
    <h3>입금 완료 시 배송이 시작됩니다.</h3>
    <button class="home">다른 상품 더 구경하기</button>
    <button class="order-list">주문내역 확인하기</button>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include flex-box(center, flex-start, column);
  padding: 2rem 0;
  gap: 1rem;

  h2 {
    @include font-factory($fs-5, bold);
  }

  h3 {
    @include font-factory($fs-3, bold);
    margin: 0;
  }

  .home {
    @include box(50%, 100%, $main, 0, 2rem 0 0 0, 1rem 0);
    @include font-factory($fs-1, normal, white);
    border: none;
    outline: none;
  }

  .order-list {
    @include box(50%, 100%, white, 0, 0, 1rem 0);
    @include font-factory($fs-1, normal, $main);
    border: 2px solid $main;
    outline: none;
  }

  button {
    cursor: pointer;
  }
}
</style>
