import Cookies from 'js-cookie'

const GOODS_TYPE_LIST = 'goods_type_list'
const UPLOAD_TOKEN = 'upload_token'

export function setGoodsTypeList(data) {
  Cookies.set(GOODS_TYPE_LIST, data)
}

export function getGoodsTypeList() {
  return Cookies.getJSON(GOODS_TYPE_LIST)
}

export function setUploadToken(uploadToken) {
  Cookies.set(UPLOAD_TOKEN, uploadToken)
}

export function getUploadToken() {
  return Cookies.get(UPLOAD_TOKEN)
}
