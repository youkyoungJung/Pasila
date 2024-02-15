import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useLiveendStore = defineStore('liveend', () => {
  const liveendInfo = ref(null)

  return { liveendInfo }
})
