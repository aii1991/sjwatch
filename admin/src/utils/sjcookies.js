import Cookies from 'js-cookie'

const GOODS_TYPE_LIST = 'goods_type_list'

export function setGoodsTypeList(data) {
  Cookies.set(GOODS_TYPE_LIST, data)
}

export function getGoodsTypeList() {
  return Cookies.getJSON(GOODS_TYPE_LIST)
}
