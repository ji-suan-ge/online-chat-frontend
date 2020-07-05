<template>
  <el-row type="flex"
          justify="space-between"
          :class="[{'activeChat': active}, 'groupMemberItem']"
          @click.native="showProfile">
      <el-col :span="8">
          <el-avatar size="medium" :src="user.avatar" class="avatar"></el-avatar>
      </el-col>
      <el-col :span="16" class="nicknameItem">
          <el-row class="nickname">{{ user.nickname }}</el-row>
      </el-col>
  </el-row>
</template>

<script>
    const ipc = require('electron').ipcRenderer
    export default {
      name: 'GroupMemberItem',
      props: ['user', 'active'],
      data () {
        return {
        }
      },
      methods: {
        showProfile () {
          ipc.send('friendInfo')
          ipc.send('getAcc', this.user)
        }
      },
      created () {
      }
    }
</script>

<style scoped>
  .groupMemberItem {
    height: 40px;
  }

  .avatar {
    margin-left: 5px;
    margin-top: 5px;
    height: 25px;
    width: 25px;
  }

  .nickname {
    font-size: 15px;
    margin-top: 10px;
  }

  .account {
    color: gray;
    margin-top: 10px;
  }

  .activeChat {
    background-color: #f8f8f8;
  }
</style>
