<template>
  <el-container>
    <el-header v-text="friend ? friend.nickname + '(' + friend.account + ')' : ''" height="38px"></el-header>
    <el-main id="messageFlow">
      <MessageItem v-for="message in currentMessageList" :key="message.id" :message="message"></MessageItem>
    </el-main>
  </el-container>
</template>

<script>
    // import messageUrl from '../constant/url/messageUrl'
    // import globalRespCode from '../constant/code/globalRespCode'
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
              // console.log(friendMessage.messageList)
              if (friendMessage.messageList.length > 0) {
                let lastTime = null
                for (let message of friendMessage.messageList) {
                  let date = new Date(message.time)
                  let day = date.getDate()
                  let month = date.getMonth()
                  let year = date.getFullYear()
                  let hour = date.getHours()
                  let minute = date.getMinutes()
                  // if (lastTime)console.log((date.getTime() - lastTime.getDate()) / 1000)
                  if (lastTime && (date.getTime() - lastTime.getTime()) / 1000 < 60) {
                    message['timeString'] = ''// 连续消息
                    // if (year === lastTime.getFullYear() && month === lastTime.getMonth() && day === lastTime.getDate()) {
                    //   if (hour === lastTime.getHours() && (minute === lastTime.getMinutes() || minute === lastTime.getMinutes() + 1)) {
                    //     message['timeString'] = ''// 连续消息
                    //   } else {
                    //     if (hour < 10) hour = '0' + hour
                    //     if (minute < 10) minute = '0' + minute
                    //     message['timeString'] = hour + ':' + minute// 当天其他时段的消息
                    //   }
                    // } else {
                    //   message['timeString'] = year + '-' + (month + 1) + '-' + day// 历史消息
                    // }
                  } else { // 如果是第一条消息或者不是连续消息，打印时间戳
                    let now = new Date()
                    if (year === now.getFullYear() && month === now.getMonth() && day === now.getDate()) {
                      if (hour < 10) hour = '0' + hour
                      if (minute < 10) minute = '0' + minute
                      message['timeString'] = hour + ':' + minute// 当天消息
                    } else {
                      message['timeString'] = year + '-' + (month + 1) + '-' + day// 历史消息
                    }
                  }
                  lastTime = date
                }
              }
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
        // getMessageList () {
        //   this.axios.post(messageUrl.getNewList, {
        //     friendId: this.getCurrentChat
        //   }).then(res => {
        //     let resp = res.data
        //     if (resp.code === globalRespCode.SUCCESS) {
        //       resp.data.messageList.forEach(message => {
        //         console.log('添加中')
        //         this.$store.dispatch('addMessageAction', message)
        //       })
        //     } else {
        //       this.$message({
        //         type: 'error',
        //         message: 'get message list failed'
        //       })
        //     }
        //   }).catch(e => {
        //     this.$message({
        //       type: 'error',
        //       message: 'get message list failed'
        //     })
        //   })
        // }
      },
      created () {
        // this.getMessageList()
      },
      updated () {
        document.getElementById('messageFlow').scrollTop = 9999999999999999999
      },
      watch: {
        getCurrentChat (val) {
          // this.getMessageList()
        }
      }
}
</script>

<style scoped>
  .el-header {
    margin: 5px;
    font-size: 25px;
    border-bottom: 1px solid #eee;
    font-weight: 700;
  }
  .el-main {
    margin: 0;
    padding: 0;
  }
  #messageFlow {
    height: 56vh;
  }

</style>
