import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/order'

const addOrderApi = async (data) => {
  try {
    const res = await local.post(`${url}`, JSON.stringify(data))
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

export { addOrderApi }
