import Vue from 'vue'
import Router from 'vue-router'
import FriendInfo from '../pages/FriendInfo'
import SelfInfo from '../pages/SelfInfo'
import EditInfo from '../pages/EditInfo'
import SearchFriend from '../pages/SearchFriend'

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
    }, {
      path: '/friendInfo',
      name: 'friendInfo',
      component: FriendInfo
    }, {
      path: '/selfInfo',
      name: 'selfInfo',
      component: SelfInfo
    }, {
      path: '/editInfo',
      name: 'editInfo',
      component: EditInfo
    }, {
      path: '/searchFriend',
      name: 'searchFriend',
      component: SearchFriend
    }
  ]
})
