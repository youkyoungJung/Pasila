import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

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

export { getScript, getScriptExample }
