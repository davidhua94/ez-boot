import request from '@/utils/request'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/admin/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/admin/logout',
    method: 'post'
  })
}
/**
 * todo 移除token参数,后端此时可以直接从header中取
 * @param {} token
 */
export function getUserInfo(token) {
  return request({
    url: '/admin/info',
    method: 'get',
    params: { token }
  })
}

