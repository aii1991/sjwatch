import request from '@/utils/request'

/**
 * 添加广告
 * @param {*} goodsId 商品id
 * @param {*} coverSrc 封面图片
 */
export function addBanner(goodsId, coverSrc) {
  return request({
    url: '/banner',
    method: 'post',
    data: {
      gId: goodsId,
      src: coverSrc
    }
  })
}

/**
 * 列出广告
 */
export function listBanner() {
  return request({
    url: '/banner/4',
    method: 'get'
  })
}

/**
 * 删除广告
 */
export function deleteBanner(bannerId) {
  return request({
    url: '/banner/' + bannerId,
    method: 'delete'
  })
}

