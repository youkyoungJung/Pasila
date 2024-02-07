<script setup>
import { ref, onMounted } from 'vue'
import { useOrderListStore } from '@/stores/orderList'
import { useMemberStore } from '@/stores/member'

const props = defineProps(['id'])

let member, orderList

onMounted(() => {
  member = useMemberStore.member
  orderList = useOrderListStore.orderList
})
</script>

<template>
  <div>
    <h3>주문자</h3>
    <p>이름</p>
    <p>{{ member.name }}</p>
    <p>주소</p>
    <p>{{ member.address }}{{ member.addressDetail }}</p>

    <h3>수령자</h3>
    <input type="checkbox" />
    <span>주문자와 동일</span>
    <p>이름</p>
    <p>{{ member.name }}</p>
    <p>주소</p>
    <p>{{ member.address }}{{ member.addressDetail }}</p>
  </div>
</template>

<style lang="scss" scoped></style>
