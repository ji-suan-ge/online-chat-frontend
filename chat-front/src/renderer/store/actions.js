import {CHAT_SOCKET_MUTATION, CURRENT_CHAT_MUTATION, FRIEND_LIST_MUTATION, USER_MUTATION} from './mutations-type'

export default {
  async loginAction ({state, commit}, {token, user}) {
    console.log('loginAction')
    console.log(user)
    commit(USER_MUTATION, user)
    if (state.chatSocket == null) {
      const ws = new WebSocket('ws://192.168.43.185:8002/chatSocket?token=' + token)
      commit(CHAT_SOCKET_MUTATION, ws)
    }
    localStorage.setItem('state', JSON.stringify(state))
  },
  async changeCurrentChatAction ({state, commit}, currentChat) {
    commit(CURRENT_CHAT_MUTATION, currentChat)
    localStorage.setItem('state', JSON.stringify(state))
  },
  async changeFriendListAction ({state, commit}, friendList) {
    commit(FRIEND_LIST_MUTATION, friendList)
    localStorage.setItem('state', JSON.stringify(state))
  }
}
