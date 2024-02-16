import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useShortpingStore = defineStore('shortping', () => {
  const videoURL = ''
  const shortpingURL = ''

  return { videoURL, shortpingURL }
})
