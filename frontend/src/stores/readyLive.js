import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useMemberStore } from '@/stores/member'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const store = useMemberStore()

  const productImage = ref()
  const imageName = ref()
  const liveProduct = ref({})
  const liveScript = ref('')
  const liveChatbot = ref([])
  const liveSchedule = ref({})
  console.log(productImage.value)

  return {
    store,
    productImage,
    imageName,
    liveProduct,
    liveScript,
    liveChatbot,
    liveSchedule
  }
})
