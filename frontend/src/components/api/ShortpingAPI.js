import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()
const url = '/shortping'

const getHighlightApi = async (liveId) => {
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

const getThumbnailApi = async (id) => {
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

const sendShortpingApi = async (data) => {
  try {
    const res = await formData.post(`${url}`, data)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export { getHighlightApi, getThumbnailApi, sendShortpingApi }
