import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import goods from './modules/goods'
import getters from './getters'
import order from './modules/order'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    goods,
    order
  },
  getters
})

export default store
