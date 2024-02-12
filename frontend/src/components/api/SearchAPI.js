import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/search'

const getPopularLive = async (id) => {
  try {
    const res = await local.get(`${url}/live`, {
      parmas: {
        keyword: id
      }
    })
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
  }
}

const getVideos = async (id) => {
  try {
    const res = await local.get('shortping/summary', {
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
