import request from '@/utils/request'

/**
 * 添加商品类型
 * @param {*} goodsTypeName 商品类型名
 */
export function addGoodsType(goodsTypeName) {
  return request({
    url: '/goods/type',
    method: 'post',
    data: {
      name: goodsTypeName
    }
  })
}

/**
 * 列出所以商品类型
 */
export function listGoodsType() {
  return request({
    url: '/goods/type',
    method: 'get'
  })
}

/**
 * 删除商品类型
 * @param {*} goodsTypeId 商品类型id
 */
export function deleteGoodsType(goodsTypeId) {
  return request({
    url: '/goods/type/' + goodsTypeId,
    method: 'delete'
  })
}

/**
 * 添加品牌
 * @param {*} name 品牌名
 * @param {*} logo 品牌logo
 * @param {*} goodsTypeId 品牌所属商品类型id
 */
export function addGoodsBrand(name, logo, goodsTypeId, descr, bgColor) {
  return request({
    url: '/goods/brand',
    method: 'post',
    data: {
      name,
      logo,
      goodsTypeId,
      descr,
      bgColor
    }
  })
}

/**
 * 列出所有商品品牌
 */
export function listGoodsBrand(goodsTypeId) {
  var reqUrl = '/goods/brand/'
  if (goodsTypeId !== 0) {
    reqUrl += goodsTypeId
  }
  return request({
    url: reqUrl,
    method: 'get'
  })
}

/**
 * 删除品牌
 * @param {*} goodsBrandId 品牌id
 */
export function deleteGoodsBrand(goodsBrandId) {
  return request({
    url: 'goods/brand/' + goodsBrandId,
    method: 'delete'
  })
}

/**
 * 列出所有商品
 * @param {*} param
 */
export function listGoods(currentPage, pageSize, param) {
  return request({
    url: '/goods/' + currentPage + '/' + pageSize + '?param=' + encodeURIComponent(JSON.stringify(param)),
    method: 'get'
  })
}

/**
 * 添加商品
 * @param {*} data
 */
export function addGoods(data) {
  return request({
    url: '/goods',
    method: 'post',
    data: data
  })
}

/**
 * 修改请求参数
 * @param {*} data
 */
export function modifyGoods(data) {
  return request({
    url: '/goods',
    method: 'put',
    data: data
  })
}

/**
 * 通过商品id获取商品
 * @param {} goodsId 商品id
 */
export function getGoodsById(goodsId) {
  return request({
    url: '/goods/' + goodsId,
    method: 'GET'
  })
}

/**
 * 删除商品
 * @param {*} goodsId 商品id
 */
export function deleteGoods(goodsId) {
  return request({
    url: '/goods/' + goodsId,
    method: 'delete'
  })
}
