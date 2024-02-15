import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMemberStore = defineStore('member', () => {
  const checkPwEmail = ref('')

  return { checkPwEmail }
})
