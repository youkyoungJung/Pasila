import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const readyProduct = ref({})
  const product = ref(null)
  return { readyProduct, product }
})
