import {
  ADD_MESSAGE_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION,
  ONLINE_MUTATION,
  USER_MUTATION
} from './mutations-type'
import globalConfig from '../config/globalConfig'
import SocketMessageType from '../constant/SocketMessageType'

export default {
  async loginAction ({state, commit}, {token, user}) {
    commit(USER_MUTATION, user)
    const ws = new WebSocket(globalConfig.socketHost + '/chatSocket?token=' + token)
    ws.onopen = () => {
      commit(ONLINE_MUTATION, true)
      commit(CHAT_SOCKET_MUTATION, ws)
    }
    ws.onclose = () => {
      commit(ONLINE_MUTATION, false)
      commit(CHAT_SOCKET_MUTATION, null)
    }
    ws.onmessage = (value) => {
      const socketMessage = JSON.parse(value.data)
      console.log(value)
      console.log(typeof socketMessage)
      const socketMessageType = socketMessage.socketMessageType
      console.log(socketMessageType)
      if (socketMessageType === SocketMessageType.PRIVATE_MESSAGE) {
        commit(ADD_MESSAGE_MUTATION, JSON.parse(socketMessage.data))
      }
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
  },
  async addMessageAction ({state, commit}, message) {
    commit(ADD_MESSAGE_MUTATION, message)
    localStorage.setItem('state', JSON.stringify(state))
  }
}
