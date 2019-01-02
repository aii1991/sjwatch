import { addGoodsType, listGoodsType, addGoodsBrand, listGoodsBrand, listGoods, addGoods, modifyGoods, deleteGoodsBrand, deleteGoodsType, deleteGoods, getGoodsById } from '@/api/goods'
import { setGoodsTypeList } from '@/utils/sjcookies'

const goods = {
  mutations: {
    ADD_GOODS_TYPE_SUCCESS: (state, payload) => {
      state.isFetchGoodsTypeData = true
    },
    LIST_GOODS_TYPE_SUCCESS: (state, payload) => {
      state.isFetchGoodsTypeData = false
      state.goodsTypeData = payload
    },
    ADD_GOODS_BRAND_SUCCESS: (state) => {
      state.isFetchGoodsBrandData = true
    },
    LIST_GOOD_BRAND_SUCCESS: (state, payload) => {
      state.isFetchGoodsBrandData = false
    },
    ADD_GOODS_SUCCESS: (state) => {
      state.isFetchGoodData = true
    },
    LIST_GOODS_SUCCESS: (state) => {
      state.isFetchGoodData = false
    },
    MODIFY_GOODS_SUCCESS: (state) => {
      state.isFetchGoodData = true
    },
    GET_GOODS_BY_ID_SUCCESS: (state) => {
      state.isFetchGoodData = false
    }
  },
  actions: {
    AddGoodsType({ commit }, name) {
      return new Promise((resolve, reject) => {
        addGoodsType(name).then(response => {
          commit('ADD_GOODS_TYPE_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ListGoodsType({ commit }) {
      return new Promise((resolve, reject) => {
        listGoodsType().then(response => {
          setGoodsTypeList(response)
          commit('LIST_GOODS_TYPE_SUCCESS', response)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    DeleteGoodsType({ commit }, goodsTypeId) {
      return new Promise((resolve, reject) => {
        deleteGoodsType(goodsTypeId).then(response => {
          commit('DELETE_GOODS_TYPE_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    AddGoodsBrand({ commit }, brandInfo) {
      return new Promise((resolve, reject) => {
        addGoodsBrand(brandInfo.name, brandInfo.logo, brandInfo.goodsTypeId).then(response => {
          commit('ADD_GOODS_BRAND_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ListGoodsBrand({ commit }, goodsTypeId) {
      return new Promise((resolve, reject) => {
        listGoodsBrand(goodsTypeId).then(response => {
          commit('LIST_GOOD_BRAND_SUCCESS', response)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    DeleteGoodsBrand({ commit }, goodsTypeBrandId) {
      return new Promise((resolve, reject) => {
        deleteGoodsBrand(goodsTypeBrandId).then(response => {
          commit('DELETE_GOODS_BRAND_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    AddGoods({ commit }, paramData) {
      return new Promise((resolve, reject) => {
        addGoods(paramData).then(response => {
          commit('ADD_GOODS_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ListGoods({ commit }, data) {
      return new Promise((resolve, reject) => {
        listGoods(data.currentPage, data.pageSize, data.param).then(response => {
          commit('LIST_GOODS_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ModifyGoods({ commit }, paramData) {
      return new Promise((resolve, reject) => {
        modifyGoods(paramData).then(response => {
          resolve()
          commit('MODIFY_GOODS_SUCCESS')
        }).catch(error => {
          reject(error)
        })
      })
    },
    GetGoodsById({ commit }, goodsId) {
      return new Promise((resolve, reject) => {
        getGoodsById(goodsId).then(response => {
          resolve(response)
          commit('GET_GOODS_BY_ID_SUCCESS')
        }).catch(error => {
          reject(error)
        })
      })
    },
    DeleteGoods({ commit }, goodsId) {
      return new Promise((resolve, reject) => {
        deleteGoods(goodsId).then(response => {
          resolve(response)
          commit('DELETE_GOODS_SUCCESS')
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default goods
