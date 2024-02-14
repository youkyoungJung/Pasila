import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const productImage = ref()
  const productDesc = ref()
  const liveProduct = ref({})
  const liveScript = ref('')
  const liveChatbot = ref([])
  const liveSchedule = ref({})

  return {
    productImage,
    productDesc,
    liveProduct,
    liveScript,
    liveChatbot,
    liveSchedule
  }
})
