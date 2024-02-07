import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/live'

const createSessionApi = async (sessionId) => {
  try {
    const res = await local.post(`${url}/sessions`, { customSessionId: sessionId })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const createTokenApi = async (sessionId) => {
  try {
    const res = await local.post(`${url}/sessions/${sessionId}/connections`, {})
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { createSessionApi, createTokenApi }
