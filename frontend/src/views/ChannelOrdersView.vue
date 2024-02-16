<script setup>
import { ref, onMounted } from 'vue'
import { getChannelOrdersApi } from '@/components/api/SellAPI'
import { getOrderStatusApi } from '@/components/api/OrderAPI.js'
import ProductTable from '@/components/channel/ProductTable.vue'

const props = defineProps(['channelId'])

let myProductList = ref([])
let statusList = ref([])

onMounted(async () => {
  myProductList.value = await getChannelOrdersApi(props.channelId)
  statusList.value = await getOrderStatusApi()
})
</script>

<template>
  <div class="orders">
    <section class="top">
      <h1 class="title">주문 관리</h1>
      <h3 class="subtitle">내 판매 상품의 주문, 배송 상태를 확인하고 관리하세요.</h3>
    </section>
    <section class="bottom">
      <product-table
        v-if="myProductList.length > 0"
        :products="myProductList"
        :status-list="statusList"
      />
      <div v-else class="no-order">
        아직 관리할 주문 내역이 없네요. 파시라 라이브를 통해 내 채널을 키워보세요!
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
