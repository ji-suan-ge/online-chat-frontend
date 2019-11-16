import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login-page',
      component: require('@/pages/Login').default
    }, {
      path: '/main',
      name: 'main-page',
      component: require('@/pages/MainPage').default
    }, {
      path: '/register',
      name: 'register-page',
      component: require('@/pages/Register').default
    }, {
      path: '/registerSuccess',
      name: 'registerSuccess-page',
      component: require('@/pages/RegisterSuccess').default
    }, {
      path: '/findPassword',
      name: 'findPassword-page',
      component: require('@/pages/FindPassword').default
    }
  ]
})
