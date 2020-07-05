import {
  ADD_MESSAGE_MUTATION, CHANGE_NEW_MESSAGE_NUMBER_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION, FRIEND_MESSAGE_LIST_MUTATION, INCREASE_NEW_MESSAGE_NUMBER_MUTATION,
  ONLINE_MUTATION, SORT_FRIEND_LIST_MUTATION, UPDATE_LAST_MESSAGE_MUTATION,
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
      const myId = state.user.id
      const friendMessage = {
        friendId: message.userId === myId ? message.friendId : message.userId,
        messageList: []
      }
      state.friendMessageList.push(friendMessage)
      friendMessage.messageList.push(message)
    }
  },
  [SORT_FRIEND_LIST_MUTATION] (state) {
    state.friendList.sort(function (friendA, friendB) {
      return friendA.lastMessageTime < friendB.lastMessageTime
    })
  },
  [FRIEND_MESSAGE_LIST_MUTATION] (state, friendMessageList) {
    state.friendMessageList = friendMessageList
  },
  [UPDATE_LAST_MESSAGE_MUTATION] (state, message) {
    for (const friend of state.friendList) {
      if (friend.id === message.friendId || friend.id === message.userId) {
        console.log(typeof message.time)
        friend.lastMessageTime = message.time
        break
      }
    }
  },
  [CHANGE_NEW_MESSAGE_NUMBER_MUTATION] (state, {friendId, number}) {
    for (const friend of state.friendList) {
      if (friend.id === friendId) {
        friend.newMessageNumber = number
        break
      }
    }
  },
  [INCREASE_NEW_MESSAGE_NUMBER_MUTATION] (state, friendId) {
    for (const friend of state.friendList) {
      if (friend.id === friendId) {
        friend.newMessageNumber = friend.newMessageNumber + 1
        break
      }
    }
  }
}
