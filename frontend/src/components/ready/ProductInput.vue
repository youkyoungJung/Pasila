<script setup>
import ProductOption from '@/components/ready/ProductOption.vue'
import { ref, watch } from 'vue'

const emit = defineEmits(['getProduct'])
const products = ref([{
    name: '',
    stock: '',
    price: '',
    discountPrice: '',
    per: ''
  }
])

const product = ref({
  name: '',
  description: '',
  memberId: localStorage.getItem('id'),
  categoryId: '',
  options: [
    {
      name: '',
      stock: 0,
      price: 0,
      discountPrice: 0
    },
    {
      name: '',
      stock: 0,
      price: 0,
      discountPrice: 0
    },
    {
      name: '',
      stock: 0,
      price: 0,
      discountPrice: 0
    }
  ]
})
const selectedCategory = ref('')
const category = ref(['뷰티', '음식', '패션', '라이프', '여행', '테크', '유아', '레저', '티켓'])

watch(product.value, () => {
  const idx = category.value.indexOf(selectedCategory.value) + 1
  product.value.categoryId = idx
  emit('getProduct', product.value)
})

const addOption = () => {
  products.value.push({
    name: '',
    stock: '',
    price: '',
    discountPrice: '',
    per: ''
  })
}

const removeOption = (index) => {
  products.value.splice(index, 1)
}

</script>

<template>
  <div class="content">
    <div class="product-input">
      <div class="product-category">
        <label for="category">분류</label>
        <select id="category" class="category" v-model="selectedCategory">
          <option v-for="(category, i) in category" :key="i">
            {{ category }}
          </option>
        </select>
      </div>
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
      <div class="product-stock">
        <label for="stock">구성</label>
        <div id="stock" class="stocks">
          <div v-for="(product, i) in products" :key="i">
            <div class="option-part">
              <product-option
              @product="(e) => product[i] = e"
              />
            </div>
            <div class="remove-part">
          <button class="remove-btn" @click="removeOption(index)">ㅡ</button>
        </div>
          </div>
          <button @click="addOption">옵션 추가</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.content {
  @include box(100%, 100%, none, 0, 0, 0);
  @include flex-box($align: center, $direction: column);
  @include font-factory($fs-1, null);
  margin-top: 2rem;

  .product-input {
    width: 100%;
    height: 100%;

    label {
      width: 8%;
      font-size: $fs-2;
      font-weight: bold;
    }
    .product-category {
      @include box(95%, 100%, none, 0, 0.1rem, 0.3rem);
      @include flex-box($justify: flex-start);

      select {
        width: 150px;
        height: 100%;
        background-size: 20px;
        padding: 5px 30px 5px 10px;
        border-radius: 4px;
        outline: 0 none;
        border: 0.5px solid $light-dark;
      }
      select option {
        background: $main;
        color: #fff;
        padding: 3px 0;
      }
    }
    .product-name {
      @include box(95%, 100%, none, 0, 0.1rem, 0.3rem);
      @include flex-box($justify: flex-start);

      .product-name-input {
        @include box(40%, 2rem, $light-gray, 0.3rem, 0, 0.1rem);
        border: none;
        outline: none;
        margin-top: 5px;
        padding-left: 1rem;
      }
    }

    .product-stock {
      @include box(95%, 100%, none, 0, 0.1rem, 0.3rem);
      display: flex;
      margin-bottom: 1rem;
      padding-bottom: 2rem;
      label {
        width: 8%;
        font-size: $fs-2;
        font-weight: bold;
      }
      .stocks {
        width: 100%;
        height: 100%;
      }
    }
  }
}
</style>
