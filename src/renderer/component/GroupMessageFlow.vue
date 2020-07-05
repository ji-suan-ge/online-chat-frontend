<template>
  <el-container>
    <el-header v-text="group ? group.name : ''" height="38px"></el-header>
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
  }
  .el-main {
    margin: 0;
    padding: 0;
  }
  #messageFlow {
    height: 56vh;
  }

</style>
