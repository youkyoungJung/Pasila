div
<script setup>
const props = defineProps(['data'])
</script>

<template>
  <div class="options">
    <div class="stock-option">
      <label for="options">{{ props.data.name }}</label>
      <input
        type="text"
        id="options"
        placeholder="개수 입력"
        class="product-price-input"
        :value="props.data.stock"
        @input="
          (e) => {
            $emit('formatOption', e.target.value)
          }
        "
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
              :value="props.data.price"
              @input="
                (e) => {
                  $emit('formatRegular', e.target.value)
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
            :value="props.data.per"
            @input="(e) => $emit('discount', e.target.value)"
          />%
          <input
            type="text"
            id="discountPrice"
            placeholder="할인가를 입력하세요"
            class="product-price-input"
            :value="props.data.discountPrice"
            @input="
              (e) => {
                $emit('formatDiscPrice', e.target.value)
              }
            "
          />원
        </div>
        <div class="discount-btn">
          <button @click="(e) => $emit('discount', 5)" class="price-btn">5%</button>
          <button @click="$emit('discount', 10)" class="price-btn">10%</button>
          <button @click="$emit('discount', 15)" class="price-btn">15%</button>
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
    @include flex-box();
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
