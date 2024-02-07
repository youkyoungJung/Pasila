import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/member'

const checkPassword = async (user) => {
  try {
    const res = await local.post(`${url}/${user.id}/pw`, { password: user.password })
    if (res.data.data) {
      return res.data
    } else {
      return false
    }
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getMyPage = async (user) => {
  try {
    const res = await local.get(`${url}/${user.id}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { checkPassword, getMyPage }
