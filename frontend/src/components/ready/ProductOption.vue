<script setup>
import { ref } from 'vue'
const props = defineProps(['name', 'stock', 'price', 'discountPrice', 'per'])
const emit = defineEmits(['name', 'stock', 'price', 'discountPrice'])

const options = ref({
  name: '',
  stock: '',
  price: '',
  discountPrice: '',
  per: ''
})

const product = ref({
  name: '',
  stock: 0,
  price: 0,
  discountPrice: 0
})

const discount = (per) => {
  options.value.per = per
  product.value.discountPrice = Math.round(((100 - per) / 100) * product.value.price)
  options.value.price = product.value.price.toLocaleString('ko-KR')
  options.value.discountPrice = product.value.discountPrice.toLocaleString('ko-KR')
  emit('discountPrice', product.value.discountPrice)
  emit('per', per)
}

const changeNumber = (field) => {
  const num = parseFloat(field)
  const str = num.toLocaleString('ko-KR')
  return str
}

const changeRegular = (field) => {
  const str = field.replaceAll(',', '')
  options.value.price = changeNumber(str)
  product.value.price = parseFloat(str)
}

const changeDiscount = (field) => {
  const str = field.replaceAll(',', '')
  options.value.discountPrice = changeNumber(str)
  product.value.discountPrice = parseFloat(str)
}

const changeStock = (field) => {
  const str = field.replaceAll(',', '')
  options.value.stock = changeNumber(str)
  product.value.stock = parseFloat(str)
  return options.value.stock
}
</script>

<template>
  <div class="options">
    <div class="stock-option">
      <div class="option-part">
        <label for="optionName">옵션명</label>
        <input
          type="text"
          id="optionName"
          placeholder="옵션명 입력"
          class="product-price-input"
          :value="props.name"
          @input="
            (e) => {
              $emit('name', e.target.value)
            }
          "
        />
      </div>
      <div class="option-part">
        <label for="options">재고</label>
        <input
          type="text"
          id="options"
          placeholder="개수 입력"
          class="product-price-input"
          :value="props.stock"
          @input="
            (e) => {
              options.stock = e.target.value
              changeStock(options.stock)
              $emit('stock', parseFloat(e.target.value))
            }
          "
        />
      </div>
    </div>
    <div class="product-price">
      <div id="price">
        <div class="price">
          <label for="regularPrice">정가</label>
          <div>
            <input
              type="text"
              id="regularPrice"
              placeholder="정가를 입력하세요"
              class="product-price-input"
              :value="props.price"
              @input="
                (e) => {
                  options.price = e.target.value
                  changeRegular(e.target.value)
                  $emit('price', parseFloat(e.target.value))
                }
              "
            />
            <span>원</span>
          </div>
        </div>

        <div class="price">
          <label for="discountPrice">할인율</label>
          <input
            type="number"
            class="product-percent-input"
            :value="props.per"
            @input="
              (e) => {
                options.per = e.target.value
                discount(e.target.value)
                $emit('per', e.target.value)
              }
            "
          />%
          <input
            type="text"
            id="discountPrice"
            placeholder="할인가를 입력하세요"
            class="product-price-input"
            :value="props.discountPrice"
            @input="
              (e) => {
                options.discountPrice = e.target.value
                changeDiscount(e.target.value)
                $emit('discountPrice', parseFloat(e.target.value))
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
  </div>
</template>

<style lang="scss" scoped>
.options {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding: 1rem 0;

  .stock-option {
    @include box(43%, 100%, none, 0, 0, 0);
    @include flex-box($align: flex-end, $justify: flex-start, $direction: column);
    margin-right: 0.5rem;

    .option-part {
      label {
        width: 30%;
        margin-right: 0.5rem;
        font-size: $fs-1;
        font-weight: normal;
      }
      padding: 0.2rem 0;
    }
  }
  .product-price {
    @include box(55%, 100%, none, 0, 0, 0);
    @include flex-box($align: flex-start, $justify: flex-start, $direction: column);

    .price {
      @include box(100%, null, none, 0, 0, 0);
      @include flex-box(center, space-between);
      padding: 0.2rem 0;
      margin-right: 1rem;

      span {
        margin-left: 0.3rem;
      }

      .product-percent-input {
        @include box(3rem, 2rem, $light-gray, 0.3rem, 0.1rem, 0.1rem);
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
}
</style>
