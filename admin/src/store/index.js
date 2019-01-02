import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import goods from './modules/goods'
import getters from './getters'
import order from './modules/order'
import banner from './modules/banner'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    goods,
    order,
    banner
  },
  getters
})

export default store
