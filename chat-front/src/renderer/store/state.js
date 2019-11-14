const local = localStorage.getItem('state')
console.log('localStorage')
console.log(local)
const state = local ? JSON.parse(local) : {
  chatSocket: null,
  currentChat: -1,
  user: {},
  friendList: [],
  online: false
}
export default state
