import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/member'

const getMemberApi = async (memberId) => {
  try {
    const res = await local.get(`${url}/${memberId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { getMemberApi }
