import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/auth'

const emailLogin = async (myEmail, myPassword) => {
  try {
    const res = await local.post(`${url}/login`, {
      email: myEmail,
      password: myPassword
    })
    alert('로그인 성공')
    let token = res.data.data
    localStorage.setItem('token', token)

    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
const getEmailAuthNumber = async (myEmail) => {
  const newEmail = { email: myEmail }
  console.log(newEmail)
  try {
    const res = await local.post(`${url}/email`, newEmail)
    console.log(res)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkEmailAuthNumber = async (myEmail, certiNum) => {
  try {
    const res = await local.put(`${url}/email`, {
      email: myEmail,
      authNum: certiNum
    })
    console.log(res)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
const getPhoneAuthNumber = async (phone) => {
  const newPhone = { phoneNum: phone }
  console.log(newPhone)
  try {
    const res = await local.post(`${url}/sms`, newPhone)
    console.log(res)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkPhoneAuthNumber = async (phone, certi) => {
  try {
    const res = await local.put(`${url}/sms`, {
      phoneNum: phone,
      authNum: certi
    })
    console.log(res)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export {
  emailLogin,
  getEmailAuthNumber,
  checkEmailAuthNumber,
  getPhoneAuthNumber,
  checkPhoneAuthNumber
}
