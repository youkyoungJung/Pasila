import { localAxios, formDataAxios } from '@/components/api/APIModule.js'

const local = localAxios()
const form = formDataAxios()

const url = ''

const getScript = async (deal, success, fail) => {
  await local.post(`${url}/ready/script`, JSON.stringify(deal))
  const successed = await success()
  const failed = await fail()
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
