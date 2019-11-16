<template>
  <el-container>
    <el-header v-text="friend ? friend.nickname : ''" height="38px"></el-header>
    <el-main id="messageFlow">
      <MessageItem v-for="message in currentMessageList" :key="message.id" :message="message"></MessageItem>
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
        friendMessageList () {
          return this.$store.getters.friendMessageList
        },
        currentMessageList () {
          for (const friendMessage of this.friendMessageList) {
            if (friendMessage.friendId === this.getCurrentChat) {
              return friendMessage.messageList
            }
          }
        },
        friendList () {
          return this.$store.getters.friendList
        },
        friend () {
          for (const friend of this.friendList) {
            if (friend.id === this.getCurrentChat) {
              return friend
            }
          }
        }
      },
      methods: {
        getMessageList () {
          this.axios.post(messageUrl.getNewList, {
            friendId: this.getCurrentChat
          }).then(res => {
            let resp = res.data
            if (resp.code === globalRespCode.SUCCESS) {
              resp.data.messageList.forEach(message => {
                console.log('添加中')
                this.$store.dispatch('addMessageAction', message)
              })
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
        // this.getMessageList()
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
  .el-header {
    margin: 5px;
    font-size: 25px;
  }
  .el-main {
    margin: 0;
    padding: 0;
  }
  #messageFlow {
    height: 56vh;
  }

</style>
