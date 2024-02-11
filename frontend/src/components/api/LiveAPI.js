import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const formData = formDataAxios()

const url = '/live'

const getScriptExample = async (deal, success, fail) => {
  const res = await local.get(`${url}/sheet/example`, JSON.stringify(deal))
  await success(res.data)
  await fail()
}

const getScript = async (deal, success, fail) => {
  const res = await local.post(`${url}/sheet`, JSON.stringify(deal))
  await success(res.data)
  await fail()
}

const sendLiveSchedule = async (data) => {
  try {
    const res = await formData.post(`${url}`, data)
    return res.data
  } catch (err) {
    console.error('localAxios error', err)
    return null
  }
}
export { getScript, getScriptExample, sendLiveSchedule }
