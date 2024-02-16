import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const productImage = ref()
  const productDesc = ref()
  const liveProduct = ref({})
  const liveScript = ref('')
  const liveChatbot = ref([])
  const liveSchedule = ref({})
  const onlyScript = ref('')
  const userScript = ref('')

  return {
    productImage,
    productDesc,
    liveProduct,
    liveScript,
    liveChatbot,
    liveSchedule,
    onlyScript,
    userScript
  }
})
