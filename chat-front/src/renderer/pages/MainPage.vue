<template>
    <el-container>
        <el-header>
            <el-avatar :src="user.avatar"  class="avatar" @click.native="showProfile"></el-avatar>
        </el-header>
        <MainTopBar></MainTopBar>
        <el-container>
            <el-aside width="260px">
                <FriendItem v-for="friend in friendList"
                            :key="friend.id"
                            :user="friend"
                            :active="currentChat === friend.id"></FriendItem>
                <p v-if="friendList.length === 0">您还没有好友！</p>
            </el-aside>
            <el-main>
                <MessageFlow></MessageFlow>
                <MessageEdit></MessageEdit>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import friendUrl from '../constant/url/friendUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import FriendItem from '../component/FriendItem'
    import MessageFlow from '../component/MessageFlow'
    import MessageEdit from '../component/MessageEdit'
    import messageUrl from '../constant/url/messageUrl'

    const ipc = require('electron').ipcRenderer
export default {
      name: 'MainPage',
      components: {MessageEdit, MessageFlow, FriendItem},
      data () {
        return {
        }
      },
      computed: {
        friendList () {
          return this.$store.getters.friendList
        },
        online () {
          return this.$store.getters.online
        },
        currentChat () {
          return this.$store.getters.currentChat
        },
        user () {
          return this.$store.getters.user
        }
      },
      methods: {
        async getFriendList () {
          await this.axios.post(friendUrl.getList).then(res => {
            const data = res.data
            if (data.code === globalRespCode.SUCCESS) {
              console.log('getAllFriend')
              console.log(data.data.friendList)
              this.$store.dispatch('changeFriendListAction', data.data.friendList)
            } else {
              this.$message({
                type: 'error',
                message: 'get friend list failed!'
              })
            }
          }).catch(e => {
            this.$message({
              type: 'error',
              message: 'get friend list failed!'
            })
          })
        },
        initFriendMessageList () {
          this.axios.post(messageUrl.getAllFriendMessage).then(res => {
            const friendMessageList = res.data.data.friendMessageList
            this.$store.dispatch('changeFriendMessageListAction', friendMessageList)
            if (this.friendList.length > 0) {
              this.$store.dispatch('changeCurrentChatAction', this.friendList[0].id)
            }
          })
        },
        showProfile () {
          ipc.send('selfInfo')
          ipc.send('getSelfAcc', this.user)
        }
      },
      created () {
        this.getFriendList().then(() => {
          this.initFriendMessageList()
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
    .el-avatar {
        margin: 10px 0 0 -5px;
    }
</style>
