import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/sell'

const getChannelOrdersApi = async (channelId) => {
  try {
    const res = await local.get(`${url}?id=${channelId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const getChannelOrderDetailApi = async (productId) => {
  try {
    const res = await local.get(`${url}/${productId}/detail`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

export { getChannelOrdersApi, getChannelOrderDetailApi }
