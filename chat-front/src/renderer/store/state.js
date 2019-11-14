const local = localStorage.getItem('state')
const state = local ? JSON.parse(local) : {
  chatSocket: null,
  currentChat: -1,
  user: {},
  friendList: [],
  online: false
}
export default state
