import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()
const url = '/shortping'

const getHighlight = async (liveId) => {
  try {
    const res = await local.get('highlight', {
      params: {
        id: liveId
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getThumbnail = async (id) => {
  try {
    const res = await local.get(`${url}/thumbnail`, {
      params: {
        productId: id
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const sendShortping = async (data) => {
  try {
    const res = await formData.post(`${url}`, data)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export { getHighlight, getThumbnail, sendShortping }
