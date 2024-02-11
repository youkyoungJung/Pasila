<script setup>
import { ref, computed } from 'vue'
import router from '@/router'
import { useOrderListStore } from '@/stores/orderList'
import { useMemberStore } from '@/stores/member'
import OrderProduct from '@/components/order/OrderProduct.vue'
import VLongInput from '@/components/common/VLongInput.vue'
import VShortInput from '@/components/common/VShortInput.vue'
import { addOrderApi } from '@/components/api/OrderAPI'

const props = defineProps(['id'])
const { member } = useMemberStore()
const { orderList, product, resetOrderList, resetProduct } = useOrderListStore()

const inputData = ref([
  {
    title: '이름',
    type: 'text',
    value: ''
  },
  {
    title: '주소',
    type: 'text',
    value: '',
    text: '주소검색'
  },
  {
    title: '상세주소',
    type: 'text',
    value: ''
  }
])

const isEqual = (e) => {
  if (e.target.checked) {
    inputData.value[0].value = member.name
    inputData.value[1].value = member.address
    inputData.value[2].value = member.addressDetail
  }
}

const totalQuantity = computed(() => {
  let count = 0
  for (let i = 0; i < orderList.length; i++) {
    count += orderList[i].quantity
  }
  return count.toLocaleString('kr-KR')
})

const totalPrice = computed(() => {
  let price = 0
  for (let i = 0; i < orderList.length; i++) {
    price += orderList[i].discountPrice * orderList[i].quantity
  }
  return price.toLocaleString('kr-KR')
})

const addOrder = async () => {
  const options = []

  for (let i = 0; i < orderList.length; i++) {
    let option = {
      id: orderList[i].optionId,
      quantity: orderList[i].quantity,
      price: orderList[i].discountPrice * orderList[i].quantity
    }
    options.push(option)
  }

  const data = {
    options: options,
    memberId: member.id,
    name: inputData.value[0].value,
    address: inputData.value[1].value + ' ' + inputData.value[2].value
  }

  if (await addOrderApi(data)) {
    resetOrderList
    resetProduct

    router.replace('/order/success')
  } else {
    alert('서버와의 연결이 원활하지 않습니다. 잠시 후 다시 시도해 주세요.')
  }
}
</script>

<template>
  <div class="container">
    <div class="inner">
      <h2 class="title">상품 주문하기</h2>

      <div class="title-box">
        <span class="subtitle">주문자 정보</span>
      </div>

      <div class="member-info">
        <div class="name">{{ member.name }}</div>
        <div class="address">{{ member.address }} {{ member.addressDetail }}</div>
      </div>

      <div class="title-box sub-box">
        <span class="subtitle m-right">배송 정보</span>
        <label class="equal-label">
          <input type="checkbox" @click="isEqual" />
          <span class="equal">주문자 정보와 동일</span>
        </label>
      </div>

      <div class="p-top">
        <v-long-input :data="inputData[0]" @get-data="(e) => console.log(e.target.value)" />
        <v-short-input :data="inputData[1]" />
        <v-long-input :data="inputData[2]" @get-data="(e) => console.log(e.target.value)" />
      </div>

      <div class="title-box sub-box">
        <span class="subtitle">주문 상품 정보</span>
      </div>

      <div class="order-info">
        <template v-for="(item, index) in orderList" :key="index">
          <order-product :thumb="product.thumbnail" :option="item" :title="product.name" />
        </template>
      </div>

      <div class="total">
        <span>총 {{ totalQuantity }}개</span>
        <span>총 {{ totalPrice }}원</span>
      </div>

      <div class="title-box sub-box">
        <span class="subtitle">결제 정보</span>
      </div>

      <div class="member-info">
        <span class="bank">{{ product.bank }} {{ product.account }}</span>
        <span>입금을 완료하시면 배송이 시작됩니다.</span>
      </div>

      <button class="done" @click="addOrder">주문</button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include flex-box(center, flex-start, column);
  padding: 3rem 3rem;

  .inner {
    width: 60%;

    .title {
      @include font-factory($fs-4, bold);
      margin: 0 0 2rem 0;
      text-align: center;
    }

    .sub-box {
      margin-top: 3rem;
    }

    .title-box {
      padding: 0.5rem 0.5rem;
      border-bottom: 2px solid $dark;

      .subtitle {
        @include font-factory($fs-3, bold);
      }

      .equal-label {
        cursor: pointer;

        input {
          height: 100%;
        }
      }
    }

    .member-info {
      padding: 1rem 5%;
      .name {
        @include font-factory($fs-2, bold);
      }

      .address {
        @include font-factory($fs-1, normal);
      }
    }

    .order-info {
      @include flex-box(center, center, column);
      width: 100%;
      padding: 2rem 0;
      gap: 1rem;
      border-bottom: 2px dashed $gray;
    }

    .total {
      @include flex-box(center, space-between, row);
      padding: 0.4rem 5%;

      span {
        display: inline-block;
      }
    }

    .bank {
      @include box(100%, 100%, $light-gray, 0, 0, 1rem 0.5rem);
      display: block;
    }

    .done {
      @include box(100%, 100%, $main, 0, 3rem 0 0 0, 1rem 0);
      @include font-factory($fs-1, normal, white);
      border: none;
      outline: none;
      cursor: pointer;
    }
  }
}

.m-right {
  margin-right: 1.5rem;
}

.p-top {
  padding-top: 1rem;
}
</style>
