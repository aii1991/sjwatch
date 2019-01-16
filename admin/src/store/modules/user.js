import { login, logout, listLog } from '@/api/login'
import { getToken, getUploadToken, getUserName, getNickName, getRoleId, removeUserInfo, getUid, getSex, getAge, setUserInfo } from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    uid: getUid(),
    name: getUserName(),
    nickName: getNickName(),
    avatar: 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3051523273,1778200958&fm=26&gp=0.jpg',
    sex: getSex(),
    age: getAge(),
    roleId: getRoleId(),
    uploadToken: getUploadToken()
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
    },
    LIST_LOG_SUCCESS: (state, payload) => {
      state.isFetchOrderData = false
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then(response => {
          setUserInfo(response)
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
        logout().then(() => {
          commit('SET_USER_INFO', '')
          removeUserInfo()
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
        removeUserInfo()
        resolve()
      })
    },

    ListLog({ commit }, param) {
      return new Promise((resolve, reject) => {
        listLog(param.currentPage, param.pageSize).then((response) => {
          commit('LIST_LOG_SUCCESS')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default user
