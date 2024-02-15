import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/summary'

const getPopularLiveApi = async (id) => {
  try {
    const res = await local.get(`live${url}`, {
      params: {
        categoryId: id
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getVideosApi = async (id) => {
  try {
    const res = await local.get(`shortping${url}`, {
      params: {
        categoryId: id
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

export { getPopularLiveApi , getVideosApi  }
