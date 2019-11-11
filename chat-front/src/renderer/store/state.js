const local = sessionStorage.getItem('state')
const state = local ? JSON.parse(local) : {
  chatSocket: null
}
export default state
