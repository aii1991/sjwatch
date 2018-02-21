import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/user',
    name: 'index',
    hidden: true
  },
  {
    path: '/user',
    component: Layout,
    name: 'userManager',
    meta: { title: '用户管理', icon: 'user' },
    children: [{
      path: 'index',
      name: 'user',
      component: () => import('@/views/user/index'),
      props: true,
      meta: { title: '用户', icon: 'user' }
    },
    {
      path: 'role/index',
      name: 'role',
      component: () => import('@/views/user/role/index'),
      props: true,
      meta: { title: '角色', icon: 'user' }
    }]
  },
  {
    path: '/goods',
    name: 'goodsManager',
    redirect: '/goods/type',
    component: Layout,
    meta: { title: '商品管理', icon: 'goods' },
    children: [
      {
        hidden: true,
        path: 'type/brand/index/:goodsTypeId/:goodsBrandId/:param',
        name: 'goods',
        component: () => import('@/views/goods/index'),
        props: true,
        meta: { title: '商品', icon: 'goods' }
      },
      {
        hidden: true,
        path: 'goods/edit/:param',
        name: 'goodsEdit',
        component: () => import('@/views/goods/edit'),
        props: true,
        meta: { title: '编辑', icon: 'goods' }
      },
      {
        path: 'type',
        name: 'goodsType',
        component: () => import('@/views/goods/type/index'),
        meta: { title: '商品类型', icon: 'goods' }
      },
      {
        hidden: true,
        path: 'type/brand/:goodsTypeId/:param',
        name: 'goodsBrand',
        component: () => import('@/views/goods/brand/index'),
        meta: { title: '商品品牌', icon: 'goods' },
        props: true
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

