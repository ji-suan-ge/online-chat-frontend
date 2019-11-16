<template>
  <el-row type="flex"
          justify="space-between"
          :class="{activeChat: active}"
          @click.native="changeChat">
      <el-avatar size="medium" :src="user.avatar" class="avatar"></el-avatar>
      <el-badge v-if="newMessageNumber > 0" :value="newMessageNumber" class="item">
        <div v-text="user.nickname" class="nickname"></div>
      </el-badge>
      <div v-if="!newMessageNumber || newMessageNumber <= 0" v-text="user.nickname" class="only-nickname"></div>
  </el-row>
</template>

<script>
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
        print () {
        }
      },
      created () {
      },
      watch: {
        friend: {
          deep: true,
          handler (nv, ov) {
            console.log('新值')
            console.log(nv)
            this.newMessageNumber = nv.newMessageNumber
          }
        }
      }
    }
</script>

<style scoped>
  .el-row {
    height: 80px;
  }

  .el-badge {
    margin: 20px;
  }

  .avatar {
    margin: 7px 15px;
    height: 66px;
    width: 65px;
  }

  .nickname {
    font-size: 20px;
    margin: 10px;
  }
  .only-nickname {
    font-size: 20px;
    margin: 30px;
  }
  .activeChat {
    background-color: #f8f8f8;
  }
</style>