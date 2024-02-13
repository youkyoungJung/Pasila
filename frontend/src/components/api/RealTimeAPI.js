import { localAxios } from '@/components/api/APIModule.js'

const local = localAxios()

const url = '/real-time'

const getLiveStockApi = (liveId) => {
  const event = new EventSource(`https://i10a402.p.ssafy.io/api${url}/subscribe/${liveId}`)
  return event
}

export { getLiveStockApi }
