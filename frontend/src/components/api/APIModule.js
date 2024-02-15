import axios from 'axios'

const API_URL = 'https://i10a402.p.ssafy.io/api'

const localAxios = () => {
  const instance = axios.create({
    baseURL: API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      Authorization: `Bearer ${localStorage.getItem('token')}`
    }
  })
  return instance
}

const problemJsonAxios = () => {
  const instance = axios.create({
    baseURL: API_URL,
    headers: {
      'Content-Type': 'application/problem+json',
      Authorization: `Bearer ${localStorage.getItem('token')}`
    }
  })
  return instance
}

const formDataAxios = () => {
  const instance = axios.create({
    baseURL: API_URL,
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: `Bearer ${localStorage.getItem('token')}`
    }
  })
  return instance
}

export { localAxios, problemJsonAxios, formDataAxios }
