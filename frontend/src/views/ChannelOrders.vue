<script setup>
import ProductTable from '@/components/channel/ProductTable.vue'

const myProductList = [
  {
    name: '겨울에 필수! 앙고라 니트',
    thumbnail: new URL('@/assets/img/test/live-thumb.png', import.meta.url).href,
    createdAt: '2022/01/02 11:11:00',
    ProductOptions: [
      {
        name: '핑크',
        stock: 30,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블루',
        stock: 20,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블랙',
        stock: 31,
        price: 36000,
        discountPrice: 20000
      }
    ],
    Orders: [
      {
        name: '상남자',
        address: '서울시 강남구 역삼동 23-3 동화빌라 402호',
        productOption: { name: '핑크' },
        order_cnt: 3,
        status: '준비중',
        deletedAt: '',
        price: 60000
      }
    ]
  },
  {
    name: '겨울에 필수! 앙고라 니트2',
    thumbnail: '/src/assets/img/test/live-thumb.png',
    createdAt: '2022/01/02 11:11:00',
    ProductOptions: [
      {
        name: '핑크',
        stock: 30,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블루',
        stock: 20,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블랙',
        stock: 31,
        price: 36000,
        discountPrice: 20000
      }
    ]
  },
  {
    name: '겨울에 필수! 앙고라 니트3',
    thumbnail: '/src/assets/img/test/live-thumb.png',
    createdAt: '2022/01/02 11:11:00',
    ProductOptions: [
      {
        name: '핑크',
        stock: 30,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블루',
        stock: 20,
        price: 36000,
        discountPrice: 20000
      },
      {
        name: '블랙',
        stock: 0,
        price: 36000,
        discountPrice: 20000
      }
    ]
  }
]
</script>

<template>
  <div class="orders">
    <section class="top">
      <h1 class="title">주문 관리</h1>
      <h3 class="subtitle">내 판매 상품의 주문, 배송 상태를 확인하고 관리하세요.</h3>
    </section>
    <section class="bottom">
      <product-table :products="myProductList" />
    </section>
  </div>
</template>

<style lang="scss" scoped>
.orders {
  padding: 1rem 5rem;
  .top {
    @include flex-box(flex-end, flex-start);
    .title {
      @include font-factory($fs-5, bold);
      margin: 0;
    }
    .subtitle {
      @include font-factory($fs-1, 400);
      margin: 0 0 0 1rem;
    }
  }
  .bottom {
    @include flex-box(flex-start, center);
    padding-top: 3rem;
  }
}
</style>
