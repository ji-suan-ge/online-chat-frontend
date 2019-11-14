<template>
  <el-container>
    <el-main id="messageFlow">
      <MessageItem v-for="message in messageList" :key="message.id" :message="message"></MessageItem>
    </el-main>
  </el-container>
</template>

<script>
    import messageUrl from '../constant/url/messageUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import MessageItem from './MessageItem'
    import MessageEdit from './MessageEdit'

export default {
      name: 'MessageFlow',
      components: {MessageEdit, MessageItem},
      data () {
        return {
        }
      },
      computed: {
        getCurrentChat () {
          return this.$store.getters.currentChat
        },
        messageList () {
          return this.$store.getters.messageList
        }
      },
      methods: {
        getMessageList () {
          this.axios.post(messageUrl.getList, {
            friendId: this.getCurrentChat
          }).then(res => {
            let resp = res.data
            if (resp.code === globalRespCode.SUCCESS) {
              this.$store.dispatch('changeMessageListAction', resp.data.messageList)
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
      updated () {
        document.getElementById('messageFlow').scrollTop = 9999999999999999999
      },
      watch: {
        getCurrentChat (val) {
          this.getMessageList()
        }
      }
}
</script>

<style scoped>
  .el-main {
    margin: 0;
    padding: 0;
  }
  #messageFlow {
    height: 60vh;
  }

</style>
