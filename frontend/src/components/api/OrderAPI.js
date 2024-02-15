import { localAxios, problemJsonAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const problem = problemJsonAxios()

const url = '/order'

const addOrderApi = async (data) => {
  try {
    const res = await local.post(`${url}`, JSON.stringify(data))
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const getOrderStatusApi = async () => {
  try {
    const res = await local.get(`${url}/statusValues`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const changeOrderStatusApi = async (orderId, status) => {
  try {
    const res = await problem.put(`${url}/${orderId}/status`, status)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

const getOrderListApi = async (memberId) => {
  try {
    const res = await local.get(`${url}?id=${memberId}`)
    return res.data.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}

export { addOrderApi, getOrderStatusApi, changeOrderStatusApi, getOrderListApi }
