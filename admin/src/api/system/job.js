import request from '@/utils/request'

export function listJob(query) {
  return request({
    url: '/job/list',
    method: 'get',
    params: query
  })
}

export function createJob(data) {
  return request({
    url: '/job/save',
    method: 'post',
    data
  })
}

export function getJob(id) {
  return request({
    url: '/job/' + id,
    method: 'get'
  })
}

export function updateJob(data) {
  return request({
    url: '/job/edit',
    method: 'post',
    data
  })
}

export function deleteJob(id) {
  return request({
    url: '/role/delete/' + id,
    method: 'get'
  })
}

