<template>
    <el-container>
        <el-header id="editBar" height="5vh"></el-header>
        <el-main id="inputArea">
            <el-input type="textarea"
                    :rows="2"
                    placeholder="请输入内容"
                    v-model="textarea"
            id="inputK"
            resize="none">
            </el-input>
        </el-main>
    </el-container>
</template>

<script>
    export default {
      name: 'MessageEdit',
      computed: {
        chatSocket () {
          return this.$store.getters.chatSocket
        }
      },
      data () {
        return {
          textarea: ''
        }
      },
      methods: {
        sendMessage () {
          this.chatSocket.send(JSON.stringify({
            friendId: 123,
            content: 'Hello'
          }))
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
