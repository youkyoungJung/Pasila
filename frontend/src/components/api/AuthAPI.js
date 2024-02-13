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

export { emailLogin }
