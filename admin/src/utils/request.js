import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (store.getters.token) {
      // 让每个请求携带token-- ['X-Admin-Token']为自定义key 请根据实际情况自行修改
      config.headers['X-Admin-Token'] = getToken()
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data

    if (!res) {
      MessageBox.alert('No Response!', '错误', {
        confirmButtonText: '确定',
        type: 'error'
      })
      return Promise.reject('error')
    }

    if (res.code !== '200') {
      if (res.code === '10004') {
        MessageBox.alert(res.message, '错误', {
          confirmButtonText: '确定',
          type: 'error'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload()
          })
        })
        return Promise.reject('error')
      }

      MessageBox.alert(res.message, '错误', {
        confirmButtonText: '确定',
        type: 'error'
      })
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }

    // if (res.code === 501) {
    //   MessageBox.alert('系统未登录，请重新登录', '错误', {
    //     confirmButtonText: '确定',
    //     type: 'error'
    //   }).then(() => {
    //     store.dispatch('FedLogOut').then(() => {
    //       location.reload()
    //     })
    //   })
    //   return Promise.reject('error')
    // } else if (res.code === 502) {
    //   MessageBox.alert('系统内部错误，请联系管理员维护', '错误', {
    //     confirmButtonText: '确定',
    //     type: 'error'
    //   })
    //   return Promise.reject('error')
    // } else if (res.code === 503) {
    //   MessageBox.alert('请求业务目前未支持', '警告', {
    //     confirmButtonText: '确定',
    //     type: 'error'
    //   })
    //   return Promise.reject('error')
    // } else if (res.errno === 504) {
    //   MessageBox.alert('更新数据已经失效，请刷新页面重新操作', '警告', {
    //     confirmButtonText: '确定',
    //     type: 'error'
    //   })
    //   return Promise.reject('error')
    // } else if (res.errno === 505) {
    //   MessageBox.alert('更新失败，请再尝试一次', '警告', {
    //     confirmButtonText: '确定',
    //     type: 'error'
    //   })
    //   return Promise.reject('error')
    // } else if (res.errno === 506) {
    //   MessageBox.alert('没有操作权限，请联系管理员授权', '错误', {
    //     confirmButtonText: '确定',
    //     type: 'error'
    //   })
    //   return Promise.reject('error')
    // } else if (res.errno !== 0) {
    //   // 非5xx的错误属于业务错误，留给具体页面处理
    //   return Promise.reject(response)
    // } else {
    //   return response
    // }
  }, error => {
    console.log('err' + error)// for debug
    Message({
      message: '网络异常',
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  })

export default service
