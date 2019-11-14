const local = localStorage.getItem('state')
console.log('localStorage')
console.log(local)
const state = local ? JSON.parse(local) : {
  chatSocket: {},
  currentChat: -1,
  user: {},
  friendList: []
}
export default state
