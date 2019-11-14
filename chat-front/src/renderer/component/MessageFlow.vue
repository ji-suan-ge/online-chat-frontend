<template>
  <el-container>
    <el-main>
      <MessageItem v-for="message in messageList" :key="message.id" :message="message"></MessageItem>
    </el-main>
  </el-container>
</template>

<script>
    import messageUrl from '../constant/url/messageUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import MessageItem from './MessageItem'

export default {
      name: 'MessageFlow',
      components: {MessageItem},
      data () {
        return {
          messageList: []
        }
      },
      methods: {
        getMessageList () {
          this.axios.post(messageUrl.getList, {
            friendId: this.getCurrentChat
          }).then(res => {
            let resp = res.data
            if (resp.code === globalRespCode.SUCCESS) {
              this.messageList = resp.data.messageList
            } else {
              this.$message({
                type: 'error',
                message: 'get message list failed'
              })
            }
          }).catch(e => {
            this.$message({
              type: 'error',
              message: 'get message list failed'
            })
          })
        }
      },
      created () {
        this.getMessageList()
      },
      watch: {
        getCurrentChat (val) {
          this.getMessageList()
        }
      },
      computed: {
        getCurrentChat () {
          return this.$store.getters.currentChat
        }
      }
}
</script>

<style scoped>
  .el-main {
    margin: 0;
    padding: 0;
  }

</style>