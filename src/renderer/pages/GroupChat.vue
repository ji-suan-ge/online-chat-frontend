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
                <el-container>
                    <el-button type="primary" :icon="showMember ? 'el-icon-right' : 'el-icon-back'"
                      size="mini" circle class="showMember" @click="toggleShowMember">
                    </el-button>
                  <el-main>
                    <GroupMessageFlow></GroupMessageFlow>
                    <GroupMessageEdit></GroupMessageEdit>
                  </el-main>
                  <el-aside :width="showMember ? '160px' : '0'">
                    <p class="member-title">群成员</p>
                    <GroupMemberItem v-for="groupMember in groupMemberList"
                      :key="groupMember.id"
                      :user="groupMember"></GroupMemberItem>
                  </el-aside>
                </el-container>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import groupUrl from '../constant/url/groupUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import GroupItem from '../component/GroupItem'
    import GroupMemberItem from '../component/GroupMemberItem'
    import GroupMessageFlow from '../component/GroupMessageFlow'
    import GroupMessageEdit from '../component/GroupMessageEdit'
    import messageUrl from '../constant/url/messageUrl'
    import MainTopBar from '../component/titlebar/MainTopBar'

    const ipc = require('electron').ipcRenderer
export default {
      name: 'GroupChat',
      components: {MainTopBar, GroupMessageEdit, GroupMessageFlow, GroupItem, GroupMemberItem},
      data () {
        return {
          showMember: true
        }
      },
      computed: {
        groupList () {
          return this.$store.getters.groupList
        },
        groupMemberList () {
          return this.$store.getters.groupMemberList
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
        toggleShowMember () {
          this.showMember = !this.showMember
        },
        getGroupMemberList () {
          this.axios.post('/group/getGroupUserList', {groupId: this.currentGroupChat}).then(res => {
            if (res.data.code === '0000') {
              this.$store.dispatch('addGroupMemberListAction', res.data.data.groupUserList)
            }
          })
        },
        async getGroupList () {
          await this.axios.post(groupUrl.getList, {userId: this.$store.getters.user.id}).then(res => {
            const data = res.data
            if (data.code === globalRespCode.SUCCESS) {
              this.$store.dispatch('changeGroupListAction', data.data.groupUserList)
            } else {
              this.$message({
                duration: 1000,
                type: 'error',
                message: 'get group list failed!'
              })
            }
          }).catch(e => {
            this.$message({
              duration: 1000,
              type: 'error',
              message: 'get group list failed!'
            })
          })
        },
        initGroupMessageList () {
          this.axios.post(messageUrl.getAllGroupMessage).then(res => {
            const groupMessageList = res.data.data.groupMessageList
            this.$store.dispatch('changeGroupMessageListAction', groupMessageList)
            if (this.groupList.length > 0) {
              this.$store.dispatch('changeCurrentGroupChatAction', this.groupList[0].id)
              this.getGroupMemberList()
            }
          })
        }
      },
      created () {
        this.getGroupList().then(() => {
          this.initGroupMessageList()
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
              duration: 1000,
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
      border-left: 1px solid #eee;
    }
    .showMember {
      position: absolute;
      top: 65px;
      right: 5px;
      opacity: 0.5;
    }
    .member-title {
      margin: 0 5px 0;
      font-size: 25px;
      padding: 5px 5px 7px 5px;
      border-bottom: 1px solid #eee;
    }
</style>
