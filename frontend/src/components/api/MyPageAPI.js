import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()

const url = '/member'

const checkPassword = async (user) => {
  const loginUser = {
    id: 1
  }
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
  const loginUser = {
    id: 1
  }
  try {
    const res = await local.get(`${url}/${loginUser.id}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const changeMyInfo = async (user, imageURL) => {
  const loginUser = {
    id: 1
  }
  if (user.password == null) user.password = ''
  if (user.gender == '여성') user.gender = 'F'
  else if (user.gender == '남성') user.gender = 'M'
  else user.gender = ''
  console.log(JSON.stringify(user))
  console.log(imageURL)
  try {
    const res = await local.put(`${url}/${loginUser.id}`, {
      personal_info: JSON.stringify(user),
      new_image: JSON.stringify(imageURL)
    })
    console.log(res.data)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export { checkPassword, getMyPage, changeMyInfo }
