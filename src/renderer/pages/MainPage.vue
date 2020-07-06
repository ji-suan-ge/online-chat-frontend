<template>
    <el-container>
        <el-header style="padding: 0;">
            <MainTopBar :user="user"></MainTopBar>
        </el-header>
        <el-container>
            <el-aside width="260px">
                <FriendItem v-for="friend in friendList"
                            :key="friend.id"
                            :user="friend"
                            :last-message="getLastMessage(friend.id)"
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
    import MainTopBar from '../component/titlebar/MainTopBar'

    const ipc = require('electron').ipcRenderer
export default {
      name: 'MainPage',
      components: {MainTopBar, MessageEdit, MessageFlow, FriendItem},
      data () {
        return {
          lastMessageOfFriend: {
            '1': {
              content: '23333'
            }
          }
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
            friendMessageList.forEach((item, index) => {
              if (item.messageList.length > 0) {
                this.lastMessageOfFriend[item.friendId] = item.messageList[item.messageList.length - 1]
              } else {
                this.lastMessageOfFriend[item.friendId] = null
              }
            })
            console.log(this.lastMessageOfFriend)
            this.$store.dispatch('changeFriendMessageListAction', friendMessageList)
            if (this.friendList.length > 0) {
              this.$store.dispatch('changeCurrentChatAction', this.friendList[0].id)
            }
          })
        },
        getLastMessage (friendId) {
          console.log(this.lastMessageOfFriend[friendId])
          return this.lastMessageOfFriend[friendId]
        }
      },
      created () {
        this.getFriendList().then(() => {
          this.initFriendMessageList()
        })
        ipc.on('refresh_main', () => {
          this.getFriendList().then(() => {
            this.initFriendMessageList()
          })
        })
        ipc.on('updateAvatar_main', (event, data) => {
          console.log('mainPage')
          console.log(data)
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
