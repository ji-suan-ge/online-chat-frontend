import {
  ADD_MESSAGE_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION, MESSAGE_LIST_MUTATION,
  ONLINE_MUTATION,
  USER_MUTATION
} from './mutations-type'

export default {
  [CHAT_SOCKET_MUTATION] (state, socket) {
    state.chatSocket = socket
  },
  [CURRENT_CHAT_MUTATION] (state, currentChat) {
    state.currentChat = currentChat
  },
  [USER_MUTATION] (state, user) {
    state.user = user
  },
  [FRIEND_LIST_MUTATION] (state, friendList) {
    state.friendList = friendList
  },
  [ONLINE_MUTATION] (state, online) {
    state.online = online
  },
  [ADD_MESSAGE_MUTATION] (state, message) {
    state.messageList.push(message)
  },
  [MESSAGE_LIST_MUTATION] (state, messageList) {
    state.messageList = messageList
  }
}
