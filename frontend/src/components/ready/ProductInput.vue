<script setup>
import { useReadyLiveStore } from '@/stores/readyLive'
import { ref, watch } from 'vue'

const store = useReadyLiveStore
const emit = defineEmits(['getProduct'])
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
  description: '',
  formatRegular: '',
  formatDiscPrice: '',
  formatOption1: '',
  formatOption2: '',
  formatOption3: ''
})
const productImage = ref('')

watch(product.value, () => {
  emit('getProduct', product.value)
})

const discount = (per) => {
  product.value.discountPercent = per
  product.value.discountPrice = Math.round(((100 - per) / 100) * product.value.regularPrice)
  product.value.formatDiscPrice = product.value.discountPrice.toLocaleString('en-US')
}

const changeNumber = (field) => {
  const parsedAmount = parseFloat(product.value[field].replace(/,/g, '')) || 0
  product.value[field] = parsedAmount.toLocaleString('en-US')
  const temp = product.value[field].replace(/,/g, '')
  return temp
}
const changeInput = (field) => {
  product.value.regularPrice = parseFloat(changeNumber(field))
}

const changeDiscount = (field) => {
  product.value.discountPrice = parseFloat(changeNumber(field))
}

const changeStock = (field) => {
  let cnt = 'option' + field.charAt(field.length - 1)
  product.value.stock[cnt] = parseFloat(changeNumber(field))
}
const uploadImg = (e) => {
  const file = e.target
  const reader = new FileReader()
  reader.onload = function (e) {
    productImage.value = e.target.result
    store.liveFormData.append('image', file)
  }
  reader.readAsDataURL(file.files[0])
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
                class="product-price-input"
                :value="product.formatRegular"
                @input="
                  (e) => {
                    product.formatRegular = e.target.value
                    changeInput('formatRegular')
                  }
                "
              />
              <span>원</span>
            </div>
          </div>

          <div class="price">
            <label for="discountPrice">할인가</label>
            <input
              type="number"
              class="product-percent-input"
              @input="(e) => discount(e.target.value)"
            />%
            <input
              type="text"
              id="discountPrice"
              placeholder="할인가를 입력하세요"
              class="product-price-input"
              :value="product.formatDiscPrice"
              @input="
                (e) => {
                  product.formatDiscPrice = e.target.value
                  changeDiscount('formatDiscPrice')
                }
              "
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
              :value="product.formatOption1"
              @input="
                (e) => {
                  product.formatOption1 = e.target.value
                  changeStock('formatOption1')
                }
              "
            />개
          </div>
          <div class="stock-option">
            <label for="option2">구성2</label>
            <input
              type="text"
              id="option2"
              placeholder="개수 입력"
              class="product-price-input"
              :value="product.formatOption2"
              @input="
                (e) => {
                  product.formatOption2 = e.target.value
                  changeStock('formatOption2')
                }
              "
            />개
          </div>
          <div class="stock-option">
            <label for="option3">구성3</label>
            <input
              type="text"
              id="option3"
              placeholder="개수 입력"
              class="product-price-input"
              :value="product.formatOption3"
              @input="
                (e) => {
                  product.formatOption3 = e.target.value
                  changeStock('formatOption3')
                }
              "
            />개
          </div>
        </div>
      </div>
      <div class="product-image">
        <label for="file">상품 대표 사진 등록</label>
        <div v-if="productImage != ''">
          <img :src="productImage" id="productImage" class="product-img" />
        </div>
        <input type="file" id="file" @change="uploadImg" accept="image/*" class="img-choose" />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.profile-choose {
  @include font-factory(13px, null);
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}
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
    .product-image {
      @include box(100%, 10%, white, 0, 0.2rem, 0.2rem);
      @include flex-box(flex-start, flex-start);
      margin-bottom: 1rem;

      .product-img {
        @include box(28rem, 15rem, none, 0, 0, 0);
      }

      label {
        @include font-factory(18px, null);
        cursor: pointer;
      }
      .img-choose {
        @include font-factory(13px, null);
        width: 0;
        height: 0;
        padding: 0;
        overflow: hidden;
        border: 0;
      }
    }
  }
}
</style>
