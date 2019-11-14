import {
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION,
  ONLINE_MUTATION,
  USER_MUTATION
} from './mutations-type'

export default {
  async loginAction ({state, commit}, {token, user}) {
    commit(USER_MUTATION, user)
    const ws = new WebSocket('ws://127.0.0.1:8002/chatSocket?token=' + token)
    ws.onopen = () => {
      commit(ONLINE_MUTATION, true)
      commit(CHAT_SOCKET_MUTATION, ws)
    }
    ws.onclose = () => {
      commit(ONLINE_MUTATION, false)
      commit(CHAT_SOCKET_MUTATION, null)
    }
    localStorage.setItem('state', JSON.stringify(state))
    console.log('')
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
