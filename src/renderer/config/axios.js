import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import global from './globalConfig'
// import router from '../router'

Vue.use(VueAxios, axios)
axios.defaults.baseURL = global.host
axios.defaults.withCredentials = true

// axios.interceptors.response.use(
//   response => {
//     if (response.code === '4001') {
//       router.push({
//         name: 'Login',
//         query: { redirect: router.currentRoute.fullPath }
//       })
//     }
//     return response
//   },
//   error => {
//     if (error.response) {
//       if (error.response.status === 401) {
//         router.push({
//           name: 'Login',
//           query: { redirect: router.currentRoute.fullPath }
//         })
//       }
//     }
//     return Promise.reject(error.response.data)
//   }
// )
