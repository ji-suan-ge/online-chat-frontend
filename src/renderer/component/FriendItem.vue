<template>
  <el-row type="flex"
          justify="space-between"
          :class="[{'activeChat': active}, 'friendItem']"
          @click.native="changeChat">
      <el-col :span="8">
          <el-avatar size="medium" :src="user.avatar" class="avatar" @click.native="showProfile"></el-avatar>
      </el-col>
      <el-col :span="16" class="nicknameItem">
          <el-row class="nickname">{{ user.nickname }}</el-row>
          <el-row class="account">({{ user.account }})</el-row>
      </el-col>
      <el-badge v-if="!!newMessageNumber && newMessageNumber > 0" :value="newMessageNumber">
      </el-badge>
  </el-row>
</template>

<script>
    const ipc = require('electron').ipcRenderer
    export default {
      name: 'FriendItem',
      props: ['user', 'active'],
      data () {
        return {
          newMessageNumber: 0
        }
      },
      computed: {
        currentChat () {
          return this.$store.getters.currentChat
        },
        friendList () {
          return this.$store.getters.friendList
        },
        friend () {
          for (const friend of this.friendList) {
            if (friend.id === this.user.id) {
              return friend
            }
          }
        }
      },
      methods: {
        changeChat () {
          if (this.currentChat !== this.user.id) {
            this.$store.dispatch('changeCurrentChatAction', this.user.id)
          }
        },
        showProfile () {
          ipc.send('friendInfo')
          ipc.send('getAcc', this.user)
        }
      },
      created () {
      },
      watch: {
        friend: {
          deep: true,
          handler (nv, ov) {
            this.newMessageNumber = nv.newMessageNumber
          }
        }
      }
    }
</script>

<style scoped>
  .friendItem {
    height: 80px;
  }

  .avatar {
    margin: 7px 15px;
    height: 66px;
    width: 65px;
  }

  .nicknameItem {
    margin: 7px 0;
  }

  .nickname {
    color: black;
    font-weight: 700;
    font-size: 20px;
    margin-top: 5px;
  }

  .account {
    color: gray;
    margin-top: 10px;
  }

  .activeChat {
    background-color: #f8f8f8;
  }
</style>
