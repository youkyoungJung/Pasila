import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useMemberStore = defineStore('member', () => {
  const member = ref({
    id: 12,
    email: 'acczoo@acczoo.com',
    name: '최지우',
    channel: 'ACCZOO',
    password: null,
    phone: '01010112022',
    address: '서울시 강남구 역삼동 22-1',
    addressDetail: '멀티캠퍼스',
    gender: 'F',
    birth: '2001-08-29',
    bank: '우리',
    account: 'bank-account',
    profile: 'https://cdn.amondz.com/product/59629/resize/mainImg/PSI_614776.jpeg?v=1648103982777'
  })
  return { member }
})
