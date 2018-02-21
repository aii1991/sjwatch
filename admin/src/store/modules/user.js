import { login, logout } from '@/api/login'
import { getToken, setToken, removeToken, setUid, getUid } from '@/utils/auth'
import { setUploadToken } from '@/utils/sjcookies'

const user = {
  state: {
    token: getToken(),
    uid: getUid(),
    name: '',
    nickName: '',
    avatar: '',
    sex: 0,
    age: 0,
    roleId: 0
  },

  mutations: {
    SET_USER_INFO: (state, payload) => {
      if (!payload) {
        return
      }
      state.token = payload.token
      state.uid = payload.id
      state.name = payload.userName
      state.nickName = payload.nickName
      state.sex = payload.sex
      state.age = payload.age
      state.roleId = payload.roleId
      state.uploadToken = payload.uploadToken
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(response => {
          setToken(response.token)
          setUid(response.id)
          setUploadToken(response.uploadToken)
          commit('SET_USER_INFO', response)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_USER_INFO', null)
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
