import axios from 'axios'

const API_URL = 'http://i10a402.p.ssafy.io:8080/api'

const localAxios = () => {
  const instance = axios.create({
    baseURL: API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    }
  })
  return instance
}

const formDataAxios = () => {
  const instance = axios.create({
    baseURL: API_URL,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  return instance
}

export { localAxios, formDataAxios }
