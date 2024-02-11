import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/product'

const getProductApi = async (productId) => {
  try {
    const res = await local.get(`${url}/${productId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

export { getProductApi }
