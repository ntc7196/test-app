import axios from "axios";

const baseURL = "http://localhost:3000"
const api = axios.create({
  baseURL: baseURL
});

axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    // can add response error handling here
    return Promise.reject(error)
  }
)

export default api;