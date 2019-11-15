<template>
    <el-container>
        <el-header>Header</el-header>
        <el-container>
            <el-aside width="260px">
                <FriendItem v-for="friend in friendList"
                            :key="friend.id"
                            :user="friend"
                            :active="currentChat === friend.id"></FriendItem>
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
        }
      },
      methods: {
        async getFriendList () {
          await this.axios.post(friendUrl.getList).then(res => {
            const data = res.data
            if (data.code === globalRespCode.SUCCESS) {
              this.$store.dispatch('changeFriendListAction', data.data.friendList)
              if (this.friendList.length > 0) {
                this.$store.dispatch('changeCurrentChatAction', this.friendList[0].id)
              }
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
          this.axios.post(messageUrl.getOldList, {}).then(res => {
            console.log(res)
            const friendMessageList = res.data.data.friendMessageList
            this.$store.dispatch('changeFriendMessageListAction', friendMessageList)
            // const friendMessageList = []
            // messageList = res.data.data.
          })
        }
      },
      created () {
        this.getFriendList().then(() => {
          this.initFriendMessageList()
        })
        if (this.friendList.length > 0) {
          this.$store.dispatch('changeCurrentChatAction', this.friendList[0].id)
        }
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
</style>
