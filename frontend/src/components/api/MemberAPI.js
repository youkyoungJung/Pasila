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

const getChannelApi = async (memberId) => {
  try {
    const res = await local.get(`${url}/channel/${memberId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const updateChannelDescApi = async (memberId, data) => {
  try {
    const res = await local.put(`${url}/channel/${memberId}`, data)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { getMemberApi, getChannelApi, updateChannelDescApi }
