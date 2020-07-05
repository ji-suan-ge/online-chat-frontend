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
                      @keydown.enter.native.prevent="sendMessage"
                      :disabled="currentGroupChat === -3">
            </el-input>
        </el-main>
    </el-container>
</template>

<script>

export default {
  name: 'GroupMessageEdit',
  data () {
    return {
      textarea: ''
    }
  },
  computed: {
    chatSocket () {
      return this.$store.getters.chatSocket
    },
    currentGroupChat () {
      return this.$store.getters.currentGroupChat
    }
  },
  methods: {
    sendMessage () {
      let message = {
        'toId': this.currentGroupChat,
        'content': this.textarea
      }
      let socketMessage = {
        socketMessageType: 0,
        data: JSON.stringify(message)
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
        height: 23vh;
    }
    #inputK {
        height: 23vh;
        border: none;
    }
</style>
