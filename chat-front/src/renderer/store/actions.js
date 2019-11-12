import {CHAT_SOCKET_MUTATION} from './mutations-type'

export default {
  async loginAction ({state, commit}) {
    if (state.chatSocket == null) {
      const ws = new WebSocket('ws://192.168.1.195:8002/chatSocket?token=123456')
      commit(CHAT_SOCKET_MUTATION, ws)
    }
    // ws.onopen = function () {
    //   // Web Socket 已连接上，使用 send() 方法发送数据
    //   ws.send('发送的数据')
    //   alert('数据发送中...')
    // }
    // ws.onmessage = function (evt) {
    //   // var received_msg = evt.data
    //   alert('数据已接收...')
    // }
    //
    // ws.onclose = function () {
    //   // 关闭 websocket
    //   alert('连接已关闭...')
    // }
  }
}
