import {
  ADD_MESSAGE_MUTATION, CHANGE_NEW_MESSAGE_NUMBER_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION, FRIEND_MESSAGE_LIST_MUTATION, INCREASE_NEW_MESSAGE_NUMBER_MUTATION,
  ONLINE_MUTATION, SORT_FRIEND_LIST_MUTATION, UPDATE_LAST_MESSAGE_MUTATION,
  USER_MUTATION, CURRENT_GROUP_CHAT_MUTATION, GROUP_LIST_MUTATION,
  ADD_GROUP_MESSAGE_MUTATION, SORT_GROUP_LIST_MUTATION,
  GROUP_MESSAGE_LIST_MUTATION, UPDATE_LAST_GROUP_MESSAGE_MUTATION,
  CHANGE_NEW_GROUP_MESSAGE_NUMBER_MUTATION, INCREASE_NEW_GROUP_MESSAGE_NUMBER_MUTATION,
  ADD_GROUP_MEMBER_LIST_MUTATION
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
  },

  [CURRENT_GROUP_CHAT_MUTATION] (state, currentGroupChat) {
    state.currentGroupChat = currentGroupChat
  },
  [GROUP_LIST_MUTATION] (state, groupList) {
    state.groupList = groupList
  },
  [ADD_GROUP_MESSAGE_MUTATION] (state, message) {
    let added = false
    for (const groupMessage of state.groupMessageList) {
      if (groupMessage.groupId === message.groupId) {
        groupMessage.messageList.push(message)
        added = true
        break
      }
    }
    if (!added) {
      const groupMessage = {
        groupId: message.groupId,
        messageList: []
      }
      state.groupMessageList.push(groupMessage)
      groupMessage.messageList.push(message)
    }
  },
  [SORT_GROUP_LIST_MUTATION] (state) {
    state.groupList.sort(function (groupA, groupB) {
      return groupA.lastMessageTime < groupB.lastMessageTime
    })
  },
  [GROUP_MESSAGE_LIST_MUTATION] (state, groupMessageList) {
    state.groupMessageList = groupMessageList
  },
  [UPDATE_LAST_GROUP_MESSAGE_MUTATION] (state, message) {
    for (const group of state.groupList) {
      if (group.id === message.groupId) {
        group.lastMessageTime = message.time
        break
      }
    }
  },
  [CHANGE_NEW_GROUP_MESSAGE_NUMBER_MUTATION] (state, {groupId, number}) {
    for (const group of state.groupList) {
      if (group.id === groupId) {
        group.newMessageNumber = number
        break
      }
    }
  },
  [INCREASE_NEW_GROUP_MESSAGE_NUMBER_MUTATION] (state, groupId) {
    for (const group of state.groupList) {
      if (group.id === groupId) {
        group.newMessageNumber = group.newMessageNumber + 1
        break
      }
    }
  },

  [ADD_GROUP_MEMBER_LIST_MUTATION] (state, groupMemberList) {
    state.groupMemberList = groupMemberList
  }
}
