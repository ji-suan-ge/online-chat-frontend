const local = sessionStorage.getItem('state')
const state = local ? JSON.parse(local) : {
  chatSocket: {},
  currentChat: -1,
  user: {}
}
export default state
