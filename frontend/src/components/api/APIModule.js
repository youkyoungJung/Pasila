import axios from 'axios'

const { VITE_VUE_API_URL } = import.meta.env

const localAxios = () => {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    }
  })
  return instance
}

const formDataAxios = () => {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  return instance
}

export { localAxios, formDataAxios }
