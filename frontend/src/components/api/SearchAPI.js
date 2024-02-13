import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/summary'

const getPopularLive = async (id) => {
  try {
    const res = await local.get(`live${url}`, {
      parmas: {
        categoryId: id
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getVideos = async (id) => {
  try {
    const res = await local.get(`shortping${url}`, {
      parmas: {
        categoryId: id
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { getPopularLive, getVideos }
