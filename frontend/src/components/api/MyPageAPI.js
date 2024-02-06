import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/member'

const checkPassword = async (user) => {
  console.log(user)
  try {
    const res = await local.get(`${url}/${user.id}/pw`, {
      params: {
        password: user.password
      }
    })
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { checkPassword }
