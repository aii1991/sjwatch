import { addOrder, listOrder, modifyOrder, deleteOrder } from '@/api/order'

const order = {
  mutations: {
    ADD_ORDER_SUCCESS: (state, payload) => {
      state.isFetchOrderData = true
    },
    LIST_ORDER_SUCCESS: (state, payload) => {
      state.isFetchOrderData = false
      state.goodsTypeData = payload
    },
    MODIFY_ORDER_SUCCESS: (state) => {
      state.isFetchOrderData = true
    },
    DELETE_ORDER_SUCCESS: (state) => {
      state.isFetchOrderData = true
    }
  },
  actions: {
    AddOrder({ commit }, data) {
      return new Promise((resolve, reject) => {
        addOrder(data).then(response => {
          commit('ADD_ORDER_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ListOrder({ commit }, data) {
      return new Promise((resolve, reject) => {
        listOrder(data.currentPage, data.pageSize, data.param).then(response => {
          commit('LIST_ORDER_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ModifyOrder({ commit }, paramData) {
      return new Promise((resolve, reject) => {
        modifyOrder(paramData).then(response => {
          resolve()
          commit('MODIFY_ORDER_SUCCESS')
        }).catch(error => {
          reject(error)
        })
      })
    },
    DeleteOrder({ commit }, goodsId) {
      return new Promise((resolve, reject) => {
        deleteOrder(goodsId).then(response => {
          resolve(response)
          commit('DELETE_ORDER_SUCCESS')
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default order
