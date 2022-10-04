import api from ".";

export const EmployeeApi = {

  create: (payload) => {
    return api.post('/employee', payload)
      .then(res => {
        return res.data
      })
  },

  get: (id) => {
    return api.get(`/employee/${id}`)
      .then(res => {
        return res.data
      })
  },

  getList: () => {
    return api.get('/employees')
      .then(res => {
        return res.data
      })
  },

  update: (id, payload) => {
    return api.put(`/employee/${id}`, payload)
      .then(res => {
        return res.data
      })
  },

  delete: (id) => {
    return api.delete(`/employee/${id}`)
      .then(res => {
        return res.data
      })
  }
}

export default EmployeeApi
