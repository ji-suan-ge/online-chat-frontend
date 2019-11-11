import {CHAT_SOCKET_MUTATION} from './mutations-type'

export default {
  [CHAT_SOCKET_MUTATION] (state, socket) {
    state.chatSocket = socket
  }
}
