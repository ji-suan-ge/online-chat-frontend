<template>
  <el-row type="flex"
          justify="space-between"
          :class="{activeChat: active}"
          @click.native="changeChat">
      <el-avatar size="medium" :src="user.avatar" class="avatar"></el-avatar>
      <div v-text="user.nickname" class="nickname"></div>
  </el-row>
</template>

<script>
    export default {
      name: 'FriendItem',
      props: ['user', 'active'],
      computed: {
        currentChat () {
          return this.$store.getters.currentChat
        }
      },
      methods: {
        changeChat () {
          if (this.currentChat !== this.user.id) {
            this.$store.dispatch('changeCurrentChatAction', this.user.id)
          }
        }
      }
    }
</script>

<style scoped>
  .el-row {
    height: 80px;
  }

  .avatar {
    margin: 7px 15px;
    height: 66px;
    width: 65px;
  }

  .nickname {
    font-size: 20px;
    height: 26px;
    margin: 27px;
  }
  .activeChat {
    background-color: #f8f8f8;
  }
</style>