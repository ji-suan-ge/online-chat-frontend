<template>
    <el-container>
        <el-header style="padding: 0;">
            <MainTopBar :user="user"></MainTopBar>
        </el-header>
        <el-container>
            <el-aside width="260px">
                <GroupItem v-for="group in groupList"
                            :key="group.id"
                            :group="group"
                            :active="currentGroupChat === group.id">
                </GroupItem>
                <p v-if="groupList.length === 0">您还没有群！</p>
            </el-aside>
            <el-main>
                <GroupMessageFlow></GroupMessageFlow>
                <GroupMessageEdit></GroupMessageEdit>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import groupUrl from '../constant/url/groupUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import GroupItem from '../component/GroupItem'
    import GroupMessageFlow from '../component/GroupMessageFlow'
    import GroupMessageEdit from '../component/GroupMessageEdit'
    import messageUrl from '../constant/url/messageUrl'
    import MainTopBar from '../component/titlebar/MainTopBar'

    const ipc = require('electron').ipcRenderer
export default {
      name: 'GroupChat',
      components: {MainTopBar, GroupMessageEdit, GroupMessageFlow, GroupItem},
      data () {
        return {
        }
      },
      computed: {
        groupList () {
          return this.$store.getters.groupList
        },
        online () {
          return this.$store.getters.online
        },
        currentGroupChat () {
          return this.$store.getters.currentGroupChat
        },
        user () {
          return this.$store.getters.user
        }
      },
      methods: {
        async getGroupList () {
          await this.axios.post(groupUrl.getList, {userId: this.$store.getters.user.id}).then(res => {
            const data = res.data
            console.log(res)
            if (data.code === globalRespCode.SUCCESS) {
              this.$store.dispatch('changeGroupListAction', data.data.groupUserList)
            } else {
              this.$message({
                type: 'error',
                message: 'get group list failed!'
              })
            }
          }).catch(e => {
            this.$message({
              type: 'error',
              message: 'get group list failed!'
            })
          })
        },
        initGroupMessageList () {
          this.axios.post(messageUrl.getAllFriendMessage).then(res => {
            const friendMessageList = res.data.data.friendMessageList
            this.$store.dispatch('changeFriendMessageListAction', friendMessageList)
            if (this.friendList.length > 0) {
              this.$store.dispatch('changeCurrentChatAction', this.friendList[0].id)
            }
          })
        }
      },
      created () {
        this.getGroupList().then(() => {
          // this.initGroupMessageList()
        })
        ipc.on('refresh_main', () => {
          this.getGroupList().then(() => {
            this.initGroupMessageList()
          })
        })
        ipc.on('updateAvatar_main', (event, data) => {
          this.$store.dispatch('avatarAction', data)
        })
      },
      watch: {
        online (val) {
          if (!val) {
            this.$message({
              type: 'error',
              message: 'offline!'
            })
          }
        }
      }
    }
</script>

<style scoped>
    .el-header {
        background-color: #f0f5fb;
    }
    .el-main {
        margin: 0;
        padding: 0;
    }
    .el-aside {
      border-right: 1px solid #eee;
    }
</style>
