import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/redis/live'

const redisStartLiveApi = async (liveId, title) => {
  try {
    const res = await local.post(`${url}/start`, { liveId: liveId, title: title })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const likeUpApi = async (liveId) => {
  try {
    const res = await local.put(`${url}/${liveId}`, {})
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

export { redisStartLiveApi, likeUpApi }
