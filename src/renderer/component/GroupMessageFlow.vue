<template>
  <el-container>
    <el-header v-text="group ? group.name + '(' + group.groupAccount + ')' : ''" height="38px"></el-header>
    <el-main id="messageFlow">
      <GroupMessageItem v-for="message in currentMessageList" :key="message.id" :message="message"></GroupMessageItem>
    </el-main>
  </el-container>
</template>

<script>
    import GroupMessageItem from './GroupMessageItem'
    import GroupMessageEdit from './GroupMessageEdit'

export default {
      name: 'GroupMessageFlow',
      components: {GroupMessageEdit, GroupMessageItem},
      data () {
        return {
        }
      },
      computed: {
        getCurrentGroupChat () {
          return this.$store.getters.currentGroupChat
        },
        groupMessageList () {
          return this.$store.getters.groupMessageList
        },
        currentMessageList () {
          for (const groupMessage of this.groupMessageList) {
            if (groupMessage.groupId === this.getCurrentGroupChat) {
              if (groupMessage.messageList.length > 0) {
                let lastTime = null
                for (let message of groupMessage.messageList) {
                  let date = new Date(message.time)
                  let day = date.getDate()
                  let month = date.getMonth()
                  let year = date.getFullYear()
                  let hour = date.getHours()
                  let minute = date.getMinutes()
                  if (lastTime) {
                    if (year === lastTime.getFullYear() && month === lastTime.getMonth() && day === lastTime.getDate()) {
                      if (hour === lastTime.getHours() && (minute === lastTime.getMinutes() || minute === lastTime.getMinutes() + 1)) {
                        message['timeString'] = ''// 连续消息
                      } else {
                        if (hour < 10) hour = '0' + hour
                        if (minute < 10) minute = '0' + minute
                        message['timeString'] = hour + ':' + minute// 当天其他时段的消息
                      }
                    } else {
                      message['timeString'] = year + '-' + (month + 1) + '-' + day// 历史消息
                    }
                  } else { // 如果是第一条消息，必定打印时间戳
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
                  console.log(message.timeString, message.time)
                }
              }
              return groupMessage.messageList
            }
          }
        },
        groupList () {
          return this.$store.getters.groupList
        },
        group () {
          for (const group of this.groupList) {
            if (group.id === this.getCurrentGroupChat) {
              return group
            }
          }
        }
      },
      methods: {
      },
      created () {
      },
      updated () {
        document.getElementById('messageFlow').scrollTop = 9999999999999999999
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
