import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useMemberStore } from '@/stores/member'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const store = useMemberStore()

  const productImage = ref()
  const productDesc = ref()
  const liveProduct = ref({})
  const liveScript = ref('')
  const liveChatbot = ref([])
  const liveSchedule = ref({})

  return {
    store,
    productImage,
    productDesc,
    liveProduct,
    liveScript,
    liveChatbot,
    liveSchedule
  }
})
