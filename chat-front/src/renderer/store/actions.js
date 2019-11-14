import {CHAT_SOCKET_MUTATION, CURRENT_CHAT_MUTATION, USER_MUTATION} from './mutations-type'

export default {
  async loginAction ({state, commit}, {token, user}) {
    console.log('loginAction')
    console.log(user)
    commit(USER_MUTATION, user)
    if (state.chatSocket == null) {
      const ws = new WebSocket('ws://192.168.43.185:8002/chatSocket?token=' + token)
      commit(CHAT_SOCKET_MUTATION, ws)
    }
  },
  async changeCurrentChatAction ({state, commit}, currentChat) {
    commit(CURRENT_CHAT_MUTATION, currentChat)
  }
}
