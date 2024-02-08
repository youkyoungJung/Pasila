<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useOrderListStore } from '@/stores/orderList'
import { useMemberStore } from '@/stores/member'
import OrderProduct from '@/components/order/OrderProduct.vue'

const props = defineProps(['id'])
const { member } = useMemberStore()
const { orderList } = useOrderListStore()

let receiver = reactive({
  name: '',
  address: '',
  addressDetail: ''
})

// watch(() => receiver, (nv, ov) => {
//   if()
// })

const isEqule = (e) => {
  if (e.target.checked) {
    receiver.name = member.name
    receiver.address = member.address
    receiver.addressDetail = member.addressDetail
  }
}
</script>

<template>
  <div class="container">
    <h3>주문자</h3>
    <p>이름</p>
    <p>{{ member.name }}</p>
    <p>주소</p>
    <p>{{ member.address }}{{ member.addressDetail }}</p>

    <h3>수령자</h3>
    <input type="checkbox" @change="isEqule" />
    <span>주문자와 동일</span>
    <p>이름</p>
    <input type="name" v-model="receiver.name" @change="changeName" />
    <p>주소</p>
    <input type="text" v-model="receiver.address" />
    <input type="text" v-model="receiver.addressDetail" />

    <template v-for="(item, index) in orderList" :key="index">
      <order-product :product="item" />
    </template>
  </div>
</template>

<style lang="scss" scoped>
.container {
  padding: 0 3rem;
}
</style>
