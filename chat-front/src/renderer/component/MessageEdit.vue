<template>
    <el-container>
        <el-header id="editBar" height="5vh"></el-header>
        <el-main id="inputArea">
            <el-input type="textarea"
                      :rows="2"
                      placeholder="请输入内容"
                      v-model="textarea"
                      id="inputK"
                      resize="none"
                      @keydown.enter.native.prevent="sendMessage">
            </el-input>
        </el-main>
    </el-container>
</template>

<script>
    import SocketMessageType from '../constant/SocketMessageType'

export default {
      name: 'MessageEdit',
      data () {
        return {
          textarea: ''
        }
      },
      computed: {
        chatSocket () {
          return this.$store.getters.chatSocket
        },
        currentChat () {
          return this.$store.getters.currentChat
        }
      },
      methods: {
        sendMessage () {
          let message = {
            'friendId': this.currentChat,
            'content': this.textarea
          }
          let socketMessage = {
            socketMessageType: SocketMessageType.PRIVATE_MESSAGE,
            data: message
          }
          this.chatSocket.send(JSON.stringify(socketMessage))
          this.textarea = ''
        }
      }
    }
</script>

<style>
    #editBar {
        margin: 0 1%;
        width: 98%;
        border-top: solid 1px #eee;
    }
    #inputArea {
        margin: 0;
        padding: 0;
        height: 26vh;
    }
    #inputK {
        height: 26vh;
        border: none;
    }
</style>
