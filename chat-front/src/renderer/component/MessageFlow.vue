<template>
  <el-container>
    <el-main>
      <el-row v-for="message in messageList" :key="message.id" v-text="message.content"></el-row>
    </el-main>
  </el-container>
</template>

<script>
    import messageUrl from '../constant/url/messageUrl'
    import globalRespCode from '../constant/code/globalRespCode'

export default {
      name: 'MessageFlow',
      data () {
        return {
          messageList: []
        }
      },
      methods: {
        getMessageList () {
          this.axios.post(messageUrl.getList, {
            friendId: this.getCurrentChat
          }).then(res => {
            console.log(res)
            let resp = res.data
            if (resp.code === globalRespCode.SUCCESS) {
              this.messageList = resp.data.messageList
            } else {
              this.$message({
                type: 'error',
                message: 'get message list failed'
              })
            }
          }).catch(e => {
            this.$message({
              type: 'error',
              message: 'get message list failed'
            })
          })
        }
      },
      created () {
      },
      watch: {
        getCurrentChat (val) {
          this.getMessageList()
        }
      },
      computed: {
        getCurrentChat () {
          return this.$store.getters.currentChat
        }
      }
}
</script>

<style scoped>

</style>