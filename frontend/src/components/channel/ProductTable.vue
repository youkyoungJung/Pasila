<script setup>
import { ref } from 'vue'
import OrderTable from '@/components/channel/OrderTable.vue'
import { getChannelOrderDetailApi } from '../api/SellAPI'

const props = defineProps({
  products: Array
})

let showIndex = ref()
let orders = ref()

const setShowIndex = async (index) => {
  orders.value = await getChannelOrderDetailApi(props.products[index].id)
  if (showIndex.value === index) {
    showIndex.value = null
    return
  }
  showIndex.value = index
}

const tableHeader = ['상품 정보', '등록일', '상품 옵션', '주문 리스트']
</script>

<template>
  <table>
    <thead>
      <tr>
        <th v-for="(header, index) in tableHeader" :key="index">{{ header }}</th>
      </tr>
    </thead>
    <tbody>
      <template v-for="(item, index) in props.products" :key="index">
        <tr>
          <td class="product">
            <div class="thumbnail" :style="{ backgroundImage: `url(${item.thumbnail})` }"></div>
            <div class="right">
              <span class="name">{{ item.name }}</span>
              <div class="price-line">
                <span class="price">{{ item.price.toLocaleString('kr-KR') }}</span>
                <span class="discount-price">{{ item.discount.toLocaleString('kr-KR') }}원</span>
              </div>
            </div>
          </td>
          <td class="date">{{ item.liveOnAt.substring(0, 10) }}</td>
          <td class="option-stock">
            <template v-for="(option, index) in item.options" :key="index">
              <div>
                <span class="name">{{ option.name }}</span>
                <span class="stock">
                  ({{ option.stock === 0 ? '품절' : option.stock + '개 남음' }})
                </span>
                <span class="name">: {{ option.discountPrice.toLocaleString('kr-KR') }}원</span>
              </div>
            </template>
          </td>
          <td class="list-btn">
            <span @click="setShowIndex(index)">
              <font-awesome-icon
                :icon="showIndex === index ? 'fa-solid fa-chevron-up' : 'fa-solid fa-chevron-down'"
              />
            </span>
          </td>
        </tr>
        <order-table v-if="showIndex === index" :orders="orders" />
      </template>
    </tbody>
  </table>
</template>

<style lang="scss" scoped>
table {
  border-collapse: collapse;
  text-align: center;
  width: 100%;
  tr {
    border-bottom: 1px solid $gray;
  }
  th {
    padding: 1.5rem 0;
  }
  tbody {
    .product {
      @include flex-box(flex-start, flex-start);
      padding: 2.5rem 2rem;
      text-align: start;
      width: max-content;
      .thumbnail {
        @include box(10rem, 5rem, 0, 1.25rem, null, null);
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
      }
      .right {
        padding: 0 1rem;
        .name {
          @include font-factory($fs-3, 500);
        }
        .price-line {
          margin-top: 1rem;
          .price {
            @include font-factory($fs-2, 500);
            text-decoration: line-through;
          }
          .discount-price {
            @include font-factory($fs-3, bold, $main);
            margin: 0 1rem;
          }
        }
      }
    }
    .date {
      padding: 0 5rem;
    }
    .option-stock {
      padding: 0 5rem;
    }
    .list-btn {
      text-align: center;
      padding: 0 5rem;
      span {
        cursor: pointer;
      }
    }
  }
}
</style>
