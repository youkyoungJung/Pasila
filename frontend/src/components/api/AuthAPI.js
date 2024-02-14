import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/auth'

const emailLogin = async (myEmail, myPassword) => {
  try {
    const res = await local.post(`${url}/login`, {
      email: myEmail,
      password: myPassword
    })
    alert(`${res.data.data.name}님! 반갑습니다 ><`)
    let token = res.data.data.token
    localStorage.setItem('token', token)

    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

// const kakao = async (url) => {
//   try {
//     const res = await local.get('https://kauth.kakao.com/oauth/authorize', {
//       params: {
//         url: url
//       }
//     })
//     alert(`${res.data.data.name}님! 반갑습니다 ><`)
//     let token = res.data.data.token
//     localStorage.setItem('token', token)
//     return res.data.data
//   } catch (err) {
//     console.error('localAxios error', err)
//   }
// }

const getEmailAuthNumber = async (myEmail) => {
  const newEmail = { email: myEmail }
  try {
    const res = await local.post(`${url}/email`, newEmail)
    return res.data.data
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
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
const getPhoneAuthNumber = async (phone) => {
  const newPhone = { phoneNum: phone }
  try {
    const res = await local.post(`${url}/sms`, newPhone)
    return res.data.data
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
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export {
  emailLogin,
  kakao,
  getEmailAuthNumber,
  checkEmailAuthNumber,
  getPhoneAuthNumber,
  checkPhoneAuthNumber
}
