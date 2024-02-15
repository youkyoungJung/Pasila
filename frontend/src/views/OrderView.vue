<script setup>
import { ref, computed, onMounted } from 'vue'
import router from '@/router'
import { useOrderListStore } from '@/stores/orderList'
import OrderProduct from '@/components/order/OrderProduct.vue'
import VLongInput from '@/components/common/VLongInput.vue'
import VShortInput from '@/components/common/VShortInput.vue'
import { addOrderApi } from '@/components/api/OrderAPI'
import { getMyPageApi } from '@/components/api/MemberAPI'

const props = defineProps(['id'])
const { orderList, product, resetOrderList, resetProduct } = useOrderListStore()
const member = ref({ name: '', address: '', addressDetail: '' })

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

onMounted(async () => {
  member.value = await getMyPageApi()
})

const isEqual = (e) => {
  if (e.target.checked) {
    inputData.value[0].value = member.value.name
    inputData.value[1].value = member.value.address
    inputData.value[2].value = member.value.addressDetail
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
    memberId: localStorage.getItem('id'),
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

const openPostCode = async () => {
  new window.daum.Postcode({
    oncomplete: (data) => {
      if (inputData.value[2].value !== '') {
        inputData.value[2].value = ''
      }
      if (data.userSelectedType === 'R') {
        // 사용자가 도로명 주소를 선택했을 경우
        inputData.value[1].value = data.roadAddress
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        inputData.value[1].value = data.jibunAddress
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === 'R') {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
          inputData.value[2].value = data.bname
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== '' && data.apartment === 'Y') {
          inputData.value[2].value +=
            inputData.value[2].value !== '' ? `, ${data.buildingName}` : data.buildingName
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (inputData.value[2].value !== '') {
          inputData.value[2].value = `(${inputData.value[2].value})`
        }
      } else {
        inputData.value[2].value = ''
      }
    }
  }).open()
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
        <v-long-input
          :input-data="inputData[0].value"
          :data="inputData[0]"
          @get-data="(e) => (inputData[0].value = e)"
        />
        <v-short-input
          :input-data="inputData[1].value"
          :data="inputData[1]"
          @get-data="(e) => (inputData[1].value = e)"
          @sendData="(e) => openPostCode(e)"
        />
        <v-long-input
          :input-data="inputData[2].value"
          :data="inputData[2]"
          @get-data="(e) => (inputData[2].value = e)"
        />
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
