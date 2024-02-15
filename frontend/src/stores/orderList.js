import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useOrderListStore = defineStore('orderList', () => {
  const orderList = ref(null)
  const product = ref(null)

  const resetOrderList = () => {
    orderList.value = null
  }

  const resetProduct = () => {
    product.value = null
  }
  return { orderList, product, resetOrderList, resetProduct }
})
