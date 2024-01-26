import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const form = formDataAxios()

const url = '/api/live'

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

const setImg = (img, success, fail) => {
  form.post(`${url}/img`, img).then(success).catch(fail)
}

const getList = (cords, success, fail) => {
  local.get(`${url}/list`, { params: cords }).then(success).catch(fail)
}

const getDeal = (id, success, fail) => {
  local.get(`${url}/deal`, { params: { id } }).then(success).catch(fail)
}

export { getScript, getScriptExample, setImg, getList, getDeal }
