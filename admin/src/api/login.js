import request from '@/utils/request'

export function login(username, password) {
  const params = new URLSearchParams()
  params.append('password', password)
  return request({
    url: '/user/' + username,
    method: 'post',
    data: params.toString(),
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

// export function getInfo(token) {
//   return request({
//     url: '/user/info',
//     method: 'get',
//     params: { token }
//   })
// }

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function listLog(currentPage, pageSize) {
  return request({
    url: '/user/log/' + currentPage + '/' + pageSize,
    method: 'get'
  })
}
