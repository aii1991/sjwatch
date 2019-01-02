import request from '@/utils/request'

/**
 * 添加订单
 * @param {*} data 订单对象
 * @param {*} code 手机验证码
 */
export function addOrder(data, code) {
  return request({
    url: '/order/' + code,
    method: 'post',
    data: data
  })
}

/**
 * 订单列表
 * @param {*} currentPage 当前页数
 * @param {*} pageSize 一个返回多少条数据
 * @param {*} param 查询参数
 */
export function listOrder(currentPage, pageSize, param) {
  return request({
    url: '/order/' + currentPage + '/' + pageSize + '?param=' + encodeURIComponent(JSON.stringify(param)),
    method: 'get'
  })
}

/**
 * 修改订单
 * @param {*} data 订单对象
 * @param {*} code 手机验证码
 */
export function modifyOrder(data, code) {
  return request({
    url: '/order/' + code,
    method: 'put',
    data: data
  })
}

/**
 * 删除订单
 * @param {*} orderId 订单id
 */
export function deleteOrder(orderId) {
  return request({
    url: '/order/' + orderId,
    method: 'delete'
  })
}

