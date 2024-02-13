<script setup>
import { ref, onMounted } from 'vue'
import { getChannelOrdersApi } from '@/components/api/SellAPI'
import ProductTable from '@/components/channel/ProductTable.vue'

const props = defineProps(['channelId'])

let myProductList = ref([])

onMounted(async () => {
  myProductList.value = await getChannelOrdersApi(props.channelId)
  myProductList.value.push({
    id: '20FD88R7Y5XM',
    thumbnail: 'https://cdn.imweb.me/thumbnail/20230208/700655507eefc.jpg',
    name: '심쿵 하트 목걸이',
    price: 30000,
    discount: 20000,
    liveOnAt: '2024-02-13T13:59:04.131294',
    options: [
      {
        id: 1,
        name: 'sliver',
        stock: 6,
        price: 30000,
        discountPrice: 20000,
        productId: '20FD88R7Y5XM'
      },
      {
        id: 2,
        name: 'gold',
        stock: 9,
        price: 30000,
        discountPrice: 20000,
        productId: '20FD88R7Y5XM'
      },
      {
        id: 3,
        name: 'black',
        stock: 19,
        price: 30000,
        discountPrice: 20000,
        productId: '20FD88R7Y5XM'
      }
    ]
  })
})
</script>

<template>
  <div class="orders">
    <section class="top">
      <h1 class="title">주문 관리</h1>
      <h3 class="subtitle">내 판매 상품의 주문, 배송 상태를 확인하고 관리하세요.</h3>
    </section>
    <section class="bottom">
      <product-table v-if="myProductList" :products="myProductList" />
      <div v-else class="no-order">
        아직 판매한 상품이 없습니다. 쉬운 방송 준비로 파는게 쉬워지는 파시라 방송을 시작해보세요!
      </div>
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

    .no-order {
      @include box(80%, 100%, $soft-pink, 20px, 0, 2rem 0);
      text-align: center;
    }
  }
}
</style>
