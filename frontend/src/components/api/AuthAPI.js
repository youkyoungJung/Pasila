import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/auth'

const emailLoginApi = async (myEmail, myPassword) => {
  try {
    const res = await local.post(`${url}/login`, {
      email: myEmail,
      password: myPassword
    })
    alert(`${res.data.data.name}님! 반갑습니다 ><`)
    let token = res.data.data.token
    let id = res.data.data.id
    let profile = res.data.data.profile
    let name = res.data.data.name
    localStorage.setItem('token', token)
    localStorage.setItem('id', id)
    localStorage.setItem('profile', profile)
    localStorage.setItem('name', name)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getEmailAuthNumberApi = async (myEmail) => {
  const newEmail = { email: myEmail }
  try {
    const res = await local.post(`${url}/email`, newEmail)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkEmailAuthNumberApi = async (myEmail, certiNum) => {
  try {
    const res = await local.put(`${url}/email`, {
      email: myEmail,
      authNum: certiNum
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
const getPhoneAuthNumberApi = async (phone) => {
  const newPhone = { phoneNum: phone }
  try {
    const res = await local.post(`${url}/sms`, newPhone)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkPhoneAuthNumberApi = async (phone, certi) => {
  try {
    const res = await local.put(`${url}/sms`, {
      phoneNum: phone,
      authNum: certi
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export {
  emailLoginApi,
  getEmailAuthNumberApi,
  checkEmailAuthNumberApi,
  getPhoneAuthNumberApi,
  checkPhoneAuthNumberApi
}
