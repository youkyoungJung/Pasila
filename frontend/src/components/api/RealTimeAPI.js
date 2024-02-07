import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/real-time'

const getLiveStockApi = async (liveId) => {
  try {
    const res = await local.get(`${url}/subscribe/${liveId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { getLiveStockApi }
