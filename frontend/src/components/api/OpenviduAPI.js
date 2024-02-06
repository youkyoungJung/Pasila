import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/live'

const createSession = async (sessionId) => {
  try {
    const res = await local.post(`${url}/sessions`, { customSessionId: sessionId })
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const createToken = async (sessionId) => {
  try {
    const res = await local.post(`${url}/sessions/${sessionId}/connections`, {})
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const offLive = async (sessionId) => {
  try {
    const res = await local.put(`${url}/${sessionId}/off`, {})
    return res.data
  } catch (err) {
    console.err('localAxios error', err)
  }
}

export { createSession, createToken, offLive }
