import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/member'

const checkPassword = async (password) => {
  try {
    const userId = '1'
    const res = await local.get(`${url}/${userId}/pw`, JSON.stringify(password))
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

// const getScript = async (deal, success, fail) => {
//   const res = await local.post(`${url}/sheet`, JSON.stringify(deal))
//   await success(res.data)
//   await fail()
// }

export { checkPassword }
