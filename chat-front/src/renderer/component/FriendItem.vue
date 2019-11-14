<template>
  <el-row type="flex" justify="space-between"
          :class="active ? 'activeChat' : ''"
          @click.native="changeChat">
      <el-image :src="user.avatar" class="avatar"></el-image>
      <p v-text="user.nickname" class="nickname"></p>
  </el-row>
</template>

<script>
    export default {
      name: 'FriendItem',
      props: ['user', 'active'],
      methods: {
        changeChat () {
          if (this.$store.getters.currentChat !== this.user.id) {
            this.$store.dispatch('changeCurrentChatAction', this.user.id)
          }
        }
      }
    }
</script>

<style scoped>
  .el-row {
    height: 65px;
    margin: 5px 0;
  }

  .avatar {
    margin-left: 15px;
    height: 65px;
    width: 65px;
  }

  .nickname {
    font-size: 20px;
    margin-right: 20px;
  }
  .activeChat {
    background-color: beige;
  }
</style>