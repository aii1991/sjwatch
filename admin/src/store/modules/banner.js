import { addBanner, listBanner, deleteBanner } from '@/api/banner'

const banner = {
  mutations: {
    ADD_BANNER_SUCCESS: (state, payload) => {
      state.isFetchOrderData = true
    },
    LIST_BANNER_SUCCESS: (state, payload) => {
      state.isFetchOrderData = false
      state.goodsTypeData = payload
    },
    DELETE_BANNER_SUCCESS: (state) => {
      state.isFetchOrderData = true
    }
  },
  actions: {
    AddBanner({ commit }, data) {
      return new Promise((resolve, reject) => {
        addBanner(data.gId, data.coverSrc).then(response => {
          commit('ADD_BANNER_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ListBanner({ commit }) {
      return new Promise((resolve, reject) => {
        listBanner().then(response => {
          commit('LIST_BANNER_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    DeleteBanner({ commit }, bannerId) {
      return new Promise((resolve, reject) => {
        deleteBanner(bannerId).then(response => {
          resolve(response)
          commit('DELETE_BANNER_SUCCESS')
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default banner
