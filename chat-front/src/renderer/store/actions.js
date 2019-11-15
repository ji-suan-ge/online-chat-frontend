import {
  ADD_MESSAGE_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION, FRIEND_MESSAGE_LIST_MUTATION,
  ONLINE_MUTATION, SORT_FRIEND_LIST_MUTATION, UPDATE_LAST_MESSAGE_MUTATION,
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
      const socketMessageType = socketMessage.socketMessageType
      if (socketMessageType === SocketMessageType.PRIVATE_MESSAGE) {
        const message = JSON.parse(socketMessage.data)
        message.time = new Date(message.time).toJSON()
        commit(ADD_MESSAGE_MUTATION, message)
        commit(UPDATE_LAST_MESSAGE_MUTATION, message)
        commit(SORT_FRIEND_LIST_MUTATION)
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
  },
  async changeFriendMessageListAction ({state, commit}, friendMessageList) {
    commit(FRIEND_MESSAGE_LIST_MUTATION, friendMessageList)
    friendMessageList.forEach(friendMessage => {
      const message = friendMessage.messageList[friendMessage.messageList.length - 1]
      commit(UPDATE_LAST_MESSAGE_MUTATION, message)
    })
  }
}
