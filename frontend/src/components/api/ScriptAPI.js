import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const form = formDataAxios()

const url = ''

const getScript = async (deal, success, fail) => {
  console.log(JSON.stringify(deal))
  const res = await local.post(`${url}/api/live/sheet`, JSON.stringify(deal))
  await success(res.json())
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

export { getScript, setImg, getList, getDeal }
