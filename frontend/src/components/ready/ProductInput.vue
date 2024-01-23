<script setup>
import editor from '@/components/editor/Editor.vue'
import { ref } from 'vue'

const product = ref({
  name: '',
  regularPrice: null,
  discountPercent: null,
  discountPrice: null,
  stock: {
    option1: null,
    option2: null,
    option3: null
  },
  explain: ''
})

const discount = (per) => {
  product.value.discountPercent = per
  product.value.discountPrice = Math.round(((100 - per) / 100) * product.value.regularPrice)
}
</script>

<template>
  <div class="container">
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
                type="number"
                id="regularPrice"
                placeholder="정가를 입력하세요"
                v-model="product.regularPrice"
                class="product-price-input"
              />
              <span>원</span>
            </div>
          </div>

          <div class="price">
            <label for="discountPrice">할인가</label>
            <input type="number" class="product-percent-input" v-model="product.discountPercent" />%
            <button @click="discount(product.discountPercent)" class="price-btn">적용</button>
            <input
              type="number"
              id="discountPrice"
              placeholder="할인가를 입력하세요"
              v-model="product.discountPrice"
              class="product-price-input"
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
              type="number"
              id="option1"
              placeholder="개수 입력"
              class="product-price-input"
            />개
          </div>
          <div class="stock-option">
            <label for="option2">구성2</label>
            <input
              type="number"
              id="option2"
              placeholder="개수 입력"
              class="product-price-input"
            />개
          </div>
          <div class="stock-option">
            <label for="option3">구성3</label>
            <input
              type="number"
              id="option3"
              placeholder="개수 입력"
              class="product-price-input"
            />개
          </div>
        </div>
      </div>
    </div>
    <div class="editor">
      <div class="editor-body">
        <editor />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.container {
  @include box(100%, 100%, none, 0, 0.8rem, 0.8rem);
  @include flex-box($align: flex-start, $direction: column);
  @include font-factory($fs-1, null);
  border-right: 1px solid $main;
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
        @include box(40%, 2rem, none, 0, 0, 0.1rem);
        border: none;

        margin-top: 5px;
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
        border-bottom: 1px solid $main;
        label {
          font-size: $fs-1;
          font-weight: normal;
        }

        margin-right: 1rem;
        span {
          margin-left: 0.35rem;
        }

        .product-percent-input {
          @include box(3rem, 2rem, none, 0, 0, 0.1rem);
          border: none;
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
      @include box(10rem, 2rem, none, 0, 0, 0.1rem);
      border: none;
      text-align: right;
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
        }
      }
    }
  }

  .editor-body {
    @include box(95%, 95%, pink, 0, 0.3rem, 0.1rem);
  }
}
</style>
