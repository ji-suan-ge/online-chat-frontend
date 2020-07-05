<template>
  <el-container>
    <el-header v-text="group ? group.name : ''" height="38px"></el-header>
    <el-main id="messageFlow">
      <MessageItem v-for="message in currentMessageList" :key="message.id" :message="message"></MessageItem>
    </el-main>
  </el-container>
</template>

<script>
    import MessageItem from './MessageItem'
    import GroupMessageEdit from './GroupMessageEdit'

export default {
      name: 'GroupMessageFlow',
      components: {GroupMessageEdit, MessageItem},
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
  }
  .el-main {
    margin: 0;
    padding: 0;
  }
  #messageFlow {
    height: 56vh;
  }

</style>
