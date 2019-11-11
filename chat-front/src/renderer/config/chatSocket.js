// import VueSocketIO from 'vue-socket.io'
// import Vue from 'vue'

// Vue.use(new VueSocketIO({
//   debug: true,
//   // 服务器端地址
//   connection: '192.168.1.195:8002',
//   vuex: {},
//   options: {
//     path: '/chatSocket',
//     transports: ['websocket']
//   }
// }))

// const ws = new WebSocket('ws://192.168.1.195:8002/chatSocket')
//
// ws.onopen = function () {
//   // Web Socket 已连接上，使用 send() 方法发送数据
//   ws.send('发送的数据')
//   alert('数据发送中...')
// }
//
// ws.onmessage = function (evt) {
//   // var received_msg = evt.data
//   alert('数据已接收...')
// }
//
// ws.onclose = function () {
//   // 关闭 websocket
//   alert('连接已关闭...')
// }
