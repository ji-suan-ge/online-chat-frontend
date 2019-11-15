const local = localStorage.getItem('state')
const state = local ? JSON.parse(local) : {
  chatSocket: null,
  currentChat: -1,
  user: {},
  friendList: [],
  online: false,
  friendMessageList: [{
    friendId: -1,
    messageList: []
  }]
}
export default state
