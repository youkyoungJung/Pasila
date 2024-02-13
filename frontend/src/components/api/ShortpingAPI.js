import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()
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

const sendShortping = async () => {
  const data = {
    video: 'string',
    shortpingRequest: {
      productId: 'string',
      title: 'string',
      livelogs: [
        {
          title: 'string',
          start: {
            hour: 0,
            minute: 0,
            second: 0,
            nano: 0
          },
          end: {
            hour: 0,
            minute: 0,
            second: 0,
            nano: 0
          },
          subtitle: 'string'
        }
      ]
    }
  }
  try {
    const res = await local.post(`${url}`, data)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}
export { getHighlight, getThumbnail }
