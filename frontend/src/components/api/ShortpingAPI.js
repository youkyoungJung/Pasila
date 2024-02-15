import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()
const url = '/shortping'

const getHighlightApi = async (liveId) => {
  try {
    const res = await local.get(`${url}/highlight`, {
      params: {
        live_id: liveId
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
        liveId: id
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

const getShortpingApi = async (shortpingId) => {
  try {
    const res = await local.get(`${url}/${shortpingId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const deleteShortpingApi = async (shortpingId) => {
  try {
    const res = await local.delete(`${url}/${shortpingId}`)
    return res
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const addLikeCntApi = async (shortpingId) => {
  try {
    const res = await local.put(`${url}/addLike`, { shortpingId: shortpingId })
    return res
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}
export {
  getHighlightApi,
  getThumbnailApi,
  sendShortpingApi,
  getShortpingApi,
  deleteShortpingApi,
  addLikeCntApi
}
