import {
  ADD_MESSAGE_MUTATION, CHANGE_NEW_MESSAGE_NUMBER_MUTATION,
  CHAT_SOCKET_MUTATION,
  CURRENT_CHAT_MUTATION,
  FRIEND_LIST_MUTATION, FRIEND_MESSAGE_LIST_MUTATION, INCREASE_NEW_MESSAGE_NUMBER_MUTATION,
  ONLINE_MUTATION, SORT_FRIEND_LIST_MUTATION, UPDATE_LAST_MESSAGE_MUTATION,
  USER_MUTATION, CURRENT_GROUP_CHAT_MUTATION, GROUP_LIST_MUTATION,
  ADD_GROUP_MESSAGE_MUTATION, SORT_GROUP_LIST_MUTATION,
  GROUP_MESSAGE_LIST_MUTATION, UPDATE_LAST_GROUP_MESSAGE_MUTATION,
  CHANGE_NEW_GROUP_MESSAGE_NUMBER_MUTATION, INCREASE_NEW_GROUP_MESSAGE_NUMBER_MUTATION
} from './mutations-type'
import globalConfig from '../config/globalConfig'
import SocketMessageType from '../constant/SocketMessageType'

export default {
  async loginAction ({state, commit}, {token, user}) {
    commit(USER_MUTATION, user)
    const ws = new WebSocket(globalConfig.socketHost + '/chatSocket/' + token)
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
        const currentChat = state.currentChat
        const message = JSON.parse(socketMessage.data)
        message.time = new Date(message.time).toJSON()
        if (message.userId !== currentChat) {
          commit(INCREASE_NEW_MESSAGE_NUMBER_MUTATION, message.userId)
        }
        commit(ADD_MESSAGE_MUTATION, message)
        commit(UPDATE_LAST_MESSAGE_MUTATION, message)
        commit(SORT_FRIEND_LIST_MUTATION)
      }

      if (socketMessageType === 0) {
        const currentGroupChat = state.currentChat
        const message = JSON.parse(socketMessage.data)
        message.time = new Date(message.time).toJSON()
        if (message.userId !== currentGroupChat) {
          commit(INCREASE_NEW_GROUP_MESSAGE_NUMBER_MUTATION, message.userId)
        }
        commit(ADD_GROUP_MESSAGE_MUTATION, message)
        commit(UPDATE_LAST_GROUP_MESSAGE_MUTATION, message)
        commit(SORT_GROUP_LIST_MUTATION)
      }
    }
  },
  async changeCurrentChatAction ({state, commit}, currentChat) {
    commit(CURRENT_CHAT_MUTATION, currentChat)
    const socketMessage = {
      socketMessageType: SocketMessageType.MARK_READ_MESSAGE,
      data: currentChat
    }
    state.chatSocket.send(JSON.stringify(socketMessage))
    commit(CHANGE_NEW_MESSAGE_NUMBER_MUTATION, {friendId: currentChat, number: 0})
  },
  async changeFriendListAction ({state, commit}, friendList) {
    commit(FRIEND_LIST_MUTATION, friendList)
  },
  async changeFriendMessageListAction ({state, commit}, friendMessageList) {
    commit(FRIEND_MESSAGE_LIST_MUTATION, friendMessageList)
    friendMessageList.forEach(friendMessage => {
      if (friendMessage.messageList.length > 0) {
        const message = friendMessage.messageList[friendMessage.messageList.length - 1]
        commit(UPDATE_LAST_MESSAGE_MUTATION, message)
      }
    })
    commit(SORT_FRIEND_LIST_MUTATION)
  },
  async avatarAction ({state, commit}, data) {
    console.log('action')
    const user = state.user
    user.avatar = data.url
    commit(USER_MUTATION, user)
  },

  async changeCurrentGroupChatAction ({state, commit}, currentGroupChat) {
    commit(CURRENT_GROUP_CHAT_MUTATION, currentGroupChat)
    const socketMessage = {
      socketMessageType: SocketMessageType.MARK_READ_MESSAGE,
      data: currentGroupChat
    }
    state.chatSocket.send(JSON.stringify(socketMessage))
    commit(CHANGE_NEW_GROUP_MESSAGE_NUMBER_MUTATION, {groupId: currentGroupChat, number: 0})
  },
  async changeGroupListAction ({state, commit}, groupList) {
    commit(GROUP_LIST_MUTATION, groupList)
  },
  async changeGroupMessageListAction ({state, commit}, groupMessageList) {
    commit(GROUP_MESSAGE_LIST_MUTATION, groupMessageList)
    groupMessageList.forEach(groupMessage => {
      const message = groupMessage.messageList[groupMessage.messageList.length - 1]
      commit(UPDATE_LAST_GROUP_MESSAGE_MUTATION, message)
    })
    commit(SORT_GROUP_LIST_MUTATION)
  }
}
