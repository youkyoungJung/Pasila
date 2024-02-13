<script setup>
defineProps({
  orders: Array
})
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
              <td>{{ order.status }}</td>
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
  border-collapse: collapse;
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
}
</style>
