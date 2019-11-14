<template>
    <el-container>
        <el-header>Header</el-header>
        <el-container>
            <el-aside width="260px">
                <FriendItem v-for="friend in friendList" :key="friend.id"
                            :user="friend"
                            :active="$store.getters.currentChat === friend.id"></FriendItem>
            </el-aside>
            <el-main>
                <MessageFlow></MessageFlow>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import friendUrl from '../constant/url/friendUrl'
    import globalRespCode from '../constant/code/globalRespCode'
    import FriendItem from '../component/FriendItem'
    import MessageFlow from '../component/MessageFlow'

export default {
      name: 'MainPage',
      components: {MessageFlow, FriendItem},
      data () {
        return {
          friendList: []
        }
      },
      methods: {
        getFriendList () {
          this.axios.post(friendUrl.getList).then(res => {
            const data = res.data
            if (data.code === globalRespCode.SUCCESS) {
              this.friendList = data.data.friendList
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
      }
    }
</script>

<style scoped>
    .el-header {
        background-color: aliceblue;
    }
    .el-aside {
        height: 89vh;
        background-color: azure;
    }
</style>