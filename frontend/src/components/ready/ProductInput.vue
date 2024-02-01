<script setup>
import { ref } from 'vue'

const product = ref({
  name: '',
  regularPrice: 0,
  discountPercent: null,
  discountPrice: null,
  stock: {
    option1: null,
    option2: null,
    option3: null
  },
  explain: '',
  formatRegular: '',
  formatDiscPrice: '',
  formatOption1: '',
  formatOption2: '',
  formatOption3: ''
})

const discount = (per) => {
  product.value.discountPercent = per
  product.value.discountPrice = Math.round(((100 - per) / 100) * product.value.regularPrice)
  product.value.formatDiscPrice = product.value.discountPrice.toLocaleString('en-US')
}

//아래 겹치는 함수는 싹다 합침필요 && 최대 개수 제한하기(int)
const changeInput = () => {
  const parsedAmount = parseFloat(product.value.formatRegular.replace(/,/g, '')) || 0
  product.value.formatRegular = parsedAmount.toLocaleString('en-US')
  const temp = product.value.formatRegular.replace(/,/g, '')
  product.value.regularPrice = parseFloat(temp)
}

const changePrice = () => {
  const parsedAmount = parseFloat(product.value.formatDiscPrice.replace(/,/g, '')) || 0
  product.value.formatDiscPrice = parsedAmount.toLocaleString('en-US')
  const temp = product.value.formatDiscPrice.replace(/,/g, '')
  product.value.discountPrice = parseFloat(temp)
}

const changeCnt1 = () => {
  const parsedAmount = parseFloat(product.value.formatOption1.replace(/,/g, '')) || 0
  product.value.formatOption1 = parsedAmount.toLocaleString('en-US')
  const temp = product.value.formatOption1.replace(/,/g, '')
  product.value.stock.option1 = parseFloat(temp)
}
const changeCnt2 = () => {
  const parsedAmount = parseFloat(product.value.formatOption2.replace(/,/g, '')) || 0
  product.value.formatOption2 = parsedAmount.toLocaleString('en-US')
  const temp = product.value.formatOption2.replace(/,/g, '')
  product.value.stock.option2 = parseFloat(temp)
}
const changeCnt3 = () => {
  const parsedAmount = parseFloat(product.value.formatOption3.replace(/,/g, '')) || 0
  product.value.formatOption3 = parsedAmount.toLocaleString('en-US')
  const temp = product.value.formatOption3.replace(/,/g, '')
  product.value.stock.option3 = parseFloat(temp)
}
</script>

<template>
  <div class="content">
    <div class="product-input">
      <div class="product-name">
        <label for="name">품명</label>
        <input
          type="text"
          id="name"
          class="product-name-input"
          placeholder="상품명을 입력하세요"
          v-model="product.name"
        />
      </div>
      <div class="product-price">
        <div>
          <label for="price">가격</label>
        </div>
        <div id="price">
          <div class="price">
            <label for="regularPrice">정가</label>
            <div>
              <input
                type="text"
                id="regularPrice"
                placeholder="정가를 입력하세요"
                v-model="product.formatRegular"
                class="product-price-input"
                @input="changeInput()"
              />
              <span>원</span>
            </div>
          </div>

          <div class="price">
            <label for="discountPrice">할인가</label>
            <input type="number" class="product-percent-input" v-model="product.discountPercent" />%
            <button @click="discount(product.discountPercent)" class="price-btn">적용</button>
            <input
              type="text"
              id="discountPrice"
              placeholder="할인가를 입력하세요"
              v-model="product.formatDiscPrice"
              class="product-price-input"
              @input="changePrice()"
            />원
          </div>
          <div class="discount-btn">
            <button @click="discount(5)" class="price-btn">5%</button>
            <button @click="discount(10)" class="price-btn">10%</button>
            <button @click="discount(15)" class="price-btn">15%</button>
          </div>
        </div>
      </div>
      <div class="product-stock">
        <label for="stock">재고</label>
        <div id="stock" class="stocks">
          <div class="stock-option">
            <label for="option1">구성1</label>
            <input
              type="text"
              id="option1"
              placeholder="개수 입력"
              class="product-price-input"
              v-model="product.formatOption1"
              @input="changeCnt1()"
            />개
          </div>
          <div class="stock-option">
            <label for="option2">구성2</label>
            <input
              type="text"
              id="option2"
              placeholder="개수 입력"
              class="product-price-input"
              v-model="product.formatOption2"
              @input="changeCnt2()"
            />개
          </div>
          <div class="stock-option">
            <label for="option3">구성3</label>
            <input
              type="text"
              id="option3"
              placeholder="개수 입력"
              class="product-price-input"
              v-model="product.formatOption3"
              @input="changeCnt3()"
            />개
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.content {
  @include box(95%, 100%, none, 0, 0.3rem, 0.1rem);
  @include flex-box($align: center, $direction: column);
  @include font-factory($fs-1, null);
  margin-top: 2rem;

  .product-input {
    @include box(95%, 95%, none, 0, 0.3rem, 0.1rem);

    label {
      margin-right: 1rem;
      font-size: $fs-2;
      font-weight: bold;
    }
    .product-name {
      @include box(95%, 100%, none, 0, 0.1rem, 0.3rem);
      @include flex-box($justify: flex-start);
      margin-bottom: 1rem;
      padding-bottom: 1rem;
      border-bottom: 2px solid $main;
      .product-name-input {
        @include box(40%, 2rem, $light-gray, 0.3rem, 0, 0.1rem);
        border: none;
        outline: none;
        margin-top: 5px;
        padding-left: 1rem;
      }
    }

    .product-price {
      @include box(95%, 100%, none, 0, 0.1rem, 0.3rem);
      @include flex-box(flex-start, flex-start);
      margin-bottom: 1rem;
      padding-bottom: 1rem;
      border-bottom: 2px solid $main;
      .price {
        @include box(100%, null, none, 0, 0.1rem, 0.1rem);
        @include flex-box(center, space-between);
        padding-bottom: 0.4rem;
        border-bottom: 1px solid $main;
        margin-right: 1rem;

        span {
          margin-left: 0.3rem;
        }
        label {
          font-size: $fs-1;
          font-weight: normal;
        }

        .product-percent-input {
          @include box(3rem, 2rem, $light-gray, 0, 0.1rem, 0.1rem);
          border: none;
          outline: none;
          text-align: right;
        }
      }
    }
    .discount-btn {
      display: flex;
      justify-content: space-between;
      padding: 0 3rem;
    }
    .price-btn {
      border: none;
      background-color: white;
      border-radius: 0.2rem;
      color: $main;
      cursor: pointer;
    }

    .product-price-input {
      @include box(10rem, 2rem, $light-gray, 0.3rem, 0.1rem, 0.1rem);
      border: none;
      outline: none;
      text-align: right;
      padding-right: 0.3rem;
    }
    .product-stock {
      @include box(95%, 100%, none, 0, 0.1rem, 0.3rem);
      @include flex-box(flex-start, flex-start);
      margin-bottom: 1rem;
      padding-bottom: 2rem;
      border-bottom: 2px solid $main;
      .stocks {
        label {
          font-size: $fs-1;
          font-weight: normal;
        }
        .stock-option {
          border-bottom: 1px solid $main;
          padding-top: 0.3rem;
          padding-bottom: 0.4rem;
        }
      }
    }
  }

  .editor-body {
    @include box(95%, 95%, $light-gray, 0, 0.3rem, 0);
  }
}
</style>
