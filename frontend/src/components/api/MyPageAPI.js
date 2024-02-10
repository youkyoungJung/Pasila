import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()

const url = '/member'
const loginUser = {
  id: 1,
  email: 'acczoo@acczoo.com'
}

const checkPassword = async (user) => {
  try {
    const res = await local.post(`${url}/${loginUser.id}/pw`, { password: user.password })
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
  try {
    const res = await local.get(`${url}/${loginUser.id}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const checkMyEmail = async (myEmail) => {
  if (loginUser.email == myEmail) return true
  try {
    const res = await local.get(`${url}/email`, {
      params: {
        email: myEmail
      }
    })
    console.log(res)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const changeMyInfo = async (data) => {
  const loginUser = {
    id: 1
  }

  try {
    const res = await formData.put(`${url}/${loginUser.id}`, data)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export { checkPassword, getMyPage, checkMyEmail, changeMyInfo }
