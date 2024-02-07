import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useOrderListStore = defineStore('orderList', () => {
  const orderList = ref(null)
  return { orderList }
})
