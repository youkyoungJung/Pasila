<script setup>
import { changeOrderStatusApi } from '@/components/api/OrderAPI.js'

defineProps({
  orders: Array,
  statusList: Array
})

const changeStatus = async (e, id) => {
  await changeOrderStatusApi(id, e.target.value)
}
</script>

<template>
  <tr>
    <td colspan="4">
      <table class="order-table">
        <thead>
          <tr>
            <th>구매자</th>
            <th>배송지</th>
            <th>구매 옵션</th>
            <th>배송 상태</th>
            <th>결제 금액</th>
          </tr>
        </thead>
        <tbody>
          <template v-for="(order, index) in orders" :key="index">
            <tr>
              <td>{{ order.buyerName }}</td>
              <td>{{ order.address }}</td>
              <td>{{ order.productOptionName }} / {{ order.orderCnt }}개</td>
              <td>
                <select
                  @change="changeStatus($event, order.id)"
                  :value="order.status"
                  class="status"
                >
                  <template v-for="(status, index) in statusList" :key="index">
                    <template v-if="index !== 0">
                      <option :value="status.key">{{ status.desc }}</option>
                    </template>
                  </template>
                </select>
              </td>
              <td>{{ order.price.toLocaleString('kr-KR') }}원</td>
            </tr>
          </template>
        </tbody>
      </table>
    </td>
  </tr>
</template>

<style lang="scss" scoped>
.order-table {
  // table-layout: fixed;
  width: 100%;
  text-align: center;
  tr {
    border-bottom: 1px solid $gray;
  }
  th {
    padding: 1.5rem 0;
  }
  td {
    padding: 1.5rem 0;
  }
  .status {
    padding: 0.3rem 0.5rem;
  }
}
</style>
