<script setup>
import { ref } from 'vue'
import router from '@/router'
import { useOrderListStore } from '@/stores/orderList'

const props = defineProps({
  liveId: String,
  product: Object
})
const orderListStore = useOrderListStore()

let myOrderList = ref([])

const addOrder = (e) => {
  const index = myOrderList.value.findIndex((el) => el.optionId === e.target.value)
  console.log('index==', index)
  if (index < 0) {
    const order = {
      optionId: e.target.value,
      optionName: props.product.options[e.target.value].name,
      quantity: 1,
      price: props.product.options[e.target.value].price,
      discountPrice: props.product.options[e.target.value].discountPrice
    }
    myOrderList.value.push(order)
  } else {
    myOrderList.value[index].quantity++
  }
}

const setQuantity = (index, num) => {
  myOrderList.value[index].quantity = myOrderList.value[index].quantity + num
  if (myOrderList.value[index].quantity <= 0) {
    myOrderList.value.splice(index, 1)
  }
}

const goPay = () => {
  if (myOrderList.value.length > 0) {
    orderListStore.orderList = myOrderList
    orderListStore.product = props.product
    //TODO: 나갈 때 session 제거 필요!
    router.push(`/live/${props.liveId}/order`)
  } else {
    alert('상품 옵션을 하나 이상 선택해주세요.')
  }
}
</script>

<template>
  <div class="live-desc-box">
    <div class="live-desc" v-dompurify-html="product.description"></div>
    <div class="order">
      <div class="order-list" v-for="(order, index) in myOrderList" :key="index">
        <span>{{ order.optionName }}</span>
        <div class="order-quantity">
          <font-awesome-icon icon="fa-regular fa-square-minus" @click="setQuantity(index, -1)" />
          <span class="num">{{ order.quantity }}</span>
          <font-awesome-icon icon="fa-regular fa-square-plus" @click="setQuantity(index, 1)" />
        </div>
      </div>
      <select data-title="옵션 선택" @change="addOrder">
        <option value="" disabled selected>옵션 선택</option>
        <template v-for="(item, index) in product.options" :key="index">
          <option :value="index">{{ item.name }}</option>
        </template>
      </select>
      <button class="order-btn" @click="goPay">주문</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.live-desc-box {
  @include flex-box(flex-start, space-between, column);
  @include box(null, calc(78vh - 5px), none, 20px, 0, 0);
  border: 3px solid $main;
  position: relative;

  .live-desc {
    padding: 1rem;
    overflow-y: scroll;
    box-shadow: 0px -4px 10px 0px rgba(0, 0, 0, 0.25) inset;
    border-radius: 20px 20px 0 0;

    &::-webkit-scrollbar {
      display: none;
    }
  }

  .order {
    @include flex-box(center, space-between, column);
    @include box(calc(100% - 2rem), fit-content, white, 0 0 20px 20px, 0, 1rem);
    gap: 0.5rem;

    .order-list {
      @include box(calc(100% - 1rem), 2rem, $light-gray, 0, 3px 0, 0.5rem);
      @include flex-box(center, space-between);

      .order-quantity {
        @include flex-box();
        .num {
          padding: 0 1rem;
        }
        span {
          display: inline-block;
        }

        svg {
          height: 1.3rem;
          cursor: pointer;
        }
      }
    }

    select {
      width: 100%;
      height: 2.3rem;
      padding: 0.3rem;
      outline: none;
      @include font-factory($fs-1, 400);
    }

    .order-btn {
      @include box(100%, 2.3rem, $main, 0, 0, 0);
      @include font-factory($fs-1, bold, white);
      border: none;
      outline: none;
      cursor: pointer;
    }
  }
}
</style>
