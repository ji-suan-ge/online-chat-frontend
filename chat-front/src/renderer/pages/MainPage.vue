<template>
    <el-container>
        <el-header>Header</el-header>
        <el-container>
            <el-aside width="260px">
                <FriendItem v-for="friend in friendList" :key="friend.id"
                            :user="friend"
                            :active="$store.getters.currentChat === friend.id"></FriendItem>
            </el-aside>
            <ChatWindow></ChatWindow>
        </el-container>
    </el-container>
</template>

<script>
    import friendUrl from '../constant/url/friendUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import FriendItem from '../component/FriendItem'
    import MessageFlow from '../component/MessageFlow'
    import MessageEdit from '../component/MessageEdit'
    import ChatWindow from '../component/ChatWindow'

export default {
      name: 'MainPage',
      components: {ChatWindow, MessageEdit, MessageFlow, FriendItem},
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
        }
      },
      methods: {
        getFriendList () {
          this.axios.post(friendUrl.getList).then(res => {
            const data = res.data
            if (data.code === globalRespCode.SUCCESS) {
              this.$store.dispatch('changeFriendListAction', data.data.friendList)
              // this.friendList = data.data.friendList
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
        }
      },
      created () {
        this.getFriendList()
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
        background-color: aliceblue;
    }
    .el-aside {
        height: 91.39vh;
        background-color: azure;
    }
</style>
