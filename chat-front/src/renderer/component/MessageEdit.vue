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
          this.chatSocket.send(JSON.stringify({
            friendId: this.currentChat,
            content: this.textarea
          }))
          let message = {
            'id': 222,
            'userId': 1,
            'friendId': this.currentChat,
            'groupId': null,
            'type': 1,
            'content': this.textarea,
            'time': '2019-11-14T11:13:51.000+0000'
          }
          this.$emit('newmessageevent', message)
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
