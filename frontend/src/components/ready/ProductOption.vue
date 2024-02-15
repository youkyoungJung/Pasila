<script setup>
import { ref, watch } from 'vue'
const emit = defineEmits(['product'])



const options = ref({
    name: '',
    stock: '',
    price: '',
    discountPrice: '',
    per: ''
})

const product = ref([{
      name: '',
      stock: 0,
      price: 0,
      discountPrice: 0
}])

watch(product, () => {
  emit('product', product.value)
  console.log(product.value)
})

const discount = (per) => {
  options.value.per = per
  product.value.discountPrice = Math.round(
    ((100 - per) / 100) * product.value.price
  )
  options.value.price = product.value.price.toLocaleString('ko-KR')
  options.value.discountPrice =
    product.value.discountPrice.toLocaleString('ko-KR')
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
}


</script>

<template>
  <div class="options">
    <div class="stock-option">
      <input type="text" id="options" placeholder="옵션명 입력" class="product-price-input" v-model="product.name" />
      <input
        type="text"
        id="options"
        placeholder="개수 입력"
        class="product-price-input"
        :value="options.stock"
        @input="(e) => {
          options.stock = e.target.value
          changeStock(options.stock)
          }"
        
      />개
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
              :value="options.price"
              @input="
                (e) => {
                  options.price = e.target.value
                  changeRegular(e.target.value)
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
            :value="options.per"
            @input="(e) => {
              options.per = e.target.value
              discount(e.target.value)
            }"
          />%
          <input
            type="text"
            id="discountPrice"
            placeholder="할인가를 입력하세요"
            class="product-price-input"
            :value="options.discountPrice"
            @input="
              (e) => {
                options.discountPrice = e.target.value
                changeDiscount(e.target.value)
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
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 2px solid $main;
  padding: 1rem 0;

  .stock-option {
    @include box(40%, 100%, none, 0, 0.1rem, 0.3rem);
    @include flex-box($direction: column);
    label {
      width: 30%;
      font-size: $fs-1;
      font-weight: normal;
    }
  }
  .product-price {
    @include box(55%, 100%, none, 0, 0.1rem, 0.3rem);
    @include flex-box();

    .price {
      @include box(100%, null, none, 0, 0, 0);
      @include flex-box(center, space-between);
      padding: 0.2rem 0;
      border-bottom: 1px solid $main;
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
