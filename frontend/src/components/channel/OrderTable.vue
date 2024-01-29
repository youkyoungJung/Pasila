<script setup>
const props = defineProps({
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
            <th>주문 취소</th>
            <th>결제 금액</th>
          </tr>
        </thead>
        <tbody>
          <template v-for="order in orders">
            <tr>
              <td>{{ order.name }}</td>
              <td>{{ order.address }}</td>
              <td>{{ order.productOption.name }}<br />{{ order.order_cnt }}개</td>
              <td>{{ order.status }}</td>
              <td><button>주문 취소</button></td>
              <td>{{ order.price.toLocaleString('kr-KR') }}</td>
            </tr>
          </template>
        </tbody>
      </table>
    </td>
  </tr>
</template>

<style lang="scss" scoped>
.order-table {
  table-layout: fixed;
  width: 100%;
}
</style>
