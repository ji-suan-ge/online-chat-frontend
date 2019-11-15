import {
  ADD_MESSAGE_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION,
  ONLINE_MUTATION, SORT_FRIEND_LIST_MUTATION,
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
    let added = false
    for (const friendMessage of state.friendMessageList) {
      if (friendMessage.friendId === message.friendId || friendMessage.friendId === message.userId) {
        friendMessage.messageList.push(message)
        added = true
        break
      }
    }
    if (!added) {
      console.log('新建')
      const myId = state.user.id
      const friendMessage = {
        friendId: message.userId === myId ? message.friendId : message.userId,
        messageList: []
      }
      state.friendMessageList.push(friendMessage)
      friendMessage.messageList.push(message)
    }
    for (const friend of state.friendList) {
      if (friend.id === message.friendId || friend.id === message.userId) {
        friend.lastMessageTiem = message.time
        break
      }
    }
  },
  [SORT_FRIEND_LIST_MUTATION] (state) {
    state.friendList.sort(function (friendA, friendB) {
      return friendA.lastMessageTiem < friendB.lastMessageTiem
    })
  }
}
