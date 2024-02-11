import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const liveFormData = new FormData()
  const liveProduct = ref({})
  const liveScript = ref('')
  const liveChatbot = ref([])
  const liveSchedule = ref({})
  return { liveFormData, liveProduct, liveScript, liveChatbot, liveSchedule }
})
