import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useShortpingStore = defineStore('shortping', () => {
  const videoURL = ref('')
  const shortpingURL = ref('')

  return { videoURL, shortpingURL }
})
