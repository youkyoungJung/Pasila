import { localAxios, formDataAxios } from '@/components/api/APIModule.js'
import { useMemberStore } from '@/stores/member'

const local = localAxios()
const formData = formDataAxios()

const url = '/member'

const joinUser = async (data) => {
  try {
    const res = await formData.post(`${url}/join`, data)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkPassword = async (user) => {
  const store = useMemberStore()
  try {
    const res = await local.post(`${url}/${store.member.id}/pw`, { password: user.password })
    if (res.data.data) {
      return res.data
    } else {
      return false
    }
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getMyPage = async () => {
  const store = useMemberStore()
  try {
    const res = await local.get(`${url}/${store.member.id}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getMemberApi = async (memberId) => {
  try {
    const res = await local.get(`${url}/${memberId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkMyEmail = async (myEmail) => {
  const store = useMemberStore()
  if (store.member.email == myEmail) return 0
  try {
    const res = await local.get(`${url}/email`, {
      params: {
        email: myEmail
      }
    })
    return res.data
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

const checkMyChannel = async (myChannel) => {
  const store = useMemberStore()
  if (store.member.channel == myChannel) return 0
  try {
    const res = await local.get(`${url}/channel`, {
      params: {
        channel: myChannel
      }
    })
    return res.data
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
const changeMyInfo = async (data) => {
  const store = useMemberStore()
  try {
    const res = await formData.put(`${url}/${store.member.id}`, data)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export {
  joinUser,
  checkPassword,
  getMyPage,
  checkMyEmail,
  checkMyChannel,
  changeMyInfo,
  getMemberApi,
  getChannelApi,
  updateChannelDescApi
}
