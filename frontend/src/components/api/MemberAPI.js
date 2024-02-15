import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()

const url = '/member'

const joinUserApi = async (data) => {
  try {
    const res = await formData.post(`${url}/join`, data)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkPasswordApi = async (user) => {
  try {
    const res = await local.post(`${url}/${localStorage.getItem('id')}/pw`, {
      password: user.password
    })
    if (res.data.data) {
      return res.data
    } else {
      return false
    }
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getMyPageApi = async () => {
  try {
    const res = await local.get(`${url}/${localStorage.getItem('id')}`)
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

const checkMyEmailApi = async (myEmail) => {
  try {
    const res = await local.get(`${url}/email`, {
      params: {
        email: myEmail
      }
    })
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

const checkMyChannelApi = async (myChannel) => {
  try {
    const res = await local.get(`${url}/channel`, {
      params: {
        channel: myChannel
      }
    })
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
const changeMyInfoApi = async (data) => {
  try {
    const res = await formData.put(`${url}/${localStorage.getItem('id')}`, data)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const updatePwApi = async (myEmail, myPassword) => {
  try {
    const res = await local.post(`${url}/updatePw`, {
      email: myEmail,
      password: myPassword
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export {
  joinUserApi,
  checkPasswordApi,
  getMyPageApi,
  checkMyEmailApi,
  checkMyChannelApi,
  changeMyInfoApi,
  getMemberApi,
  getChannelApi,
  updateChannelDescApi,
  updatePwApi
}
