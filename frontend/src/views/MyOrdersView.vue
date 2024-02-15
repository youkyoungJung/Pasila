<script setup>
import { ref, onMounted, computed } from 'vue'
import { getOrderListApi, getOrderStatusApi } from '@/components/api/OrderAPI.js'
import MyOrderProduct from '@/components/order/MyOrderProduct.vue'

const orderList = ref([])
const orderStatus = ref([])

onMounted(async () => {
  orderList.value = await getOrderListApi(2)
  orderStatus.value = await getOrderStatusApi()
})
</script>

<template>
  <div class="orders">
    <section class="top">
      <h1 class="title">주문 내역</h1>
    </section>
    <section class="bottom" v-if="orderList.length > 0">
      <my-order-product v-for="(item, index) in orderList" :key="index" :order="item" />
    </section>
    <div v-else class="no-order">
      아직 구매한 상품이 없습니다. 파시라 라이브에서 갖고싶은 제품을 찾아보세요.
    </div>
  </div>
</template>

<style lang="scss" scoped>
.orders {
  padding: 1rem 5rem;
  .top {
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
  .no-order {
    @include box(100%, 100%, $soft-pink, 20px, 2rem 0, 2rem 0);
    text-align: center;
  }
}
</style>
