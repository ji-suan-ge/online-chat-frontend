<template>
  <el-row type="flex"
          justify="space-between"
          :class="[{'activeChat': active}, {'unactiveChat': !active}, 'friendItem']"
          @click.native="changeChat">
      <el-col :span="8">
          <el-avatar size="medium" shape="square" :src="user.avatar" class="avatar" @click.native="showProfile"></el-avatar>
      </el-col>
      <el-col :span="16" class="nicknameItem">
          <el-row class="title">
            <el-col class="info">{{userInfo}}</el-col>
          </el-row>
          <el-row class="detail">
            <el-col :span="18" class="message">{{ calculateLastMessage.message }}</el-col>
            <el-col :span="6" class="time">{{ calculateLastMessage.time }}</el-col>
          </el-row>
      </el-col>
      <el-badge v-if="!!newMessageNumber && newMessageNumber > 0" :value="newMessageNumber">
      </el-badge>
  </el-row>
</template>

<script>
    const ipc = require('electron').ipcRenderer
    export default {
      name: 'FriendItem',
      props: ['user', 'active', 'lastMessage'],
      data () {
        return {
          newMessageNumber: 0,
          message: '',
          time: ''
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
        },
        userInfo () {
          return this.limitedString(this.user.nickname, 9)
        },
        calculateLastMessage () {
          let result = {
            message: '',
            time: ''
          }
          if (this.lastMessage) {
            result.message = this.lastMessage.content
            let date = new Date(this.lastMessage.time)
            let now = new Date()
            let day = date.getDate()
            let month = date.getMonth()
            let year = date.getFullYear()
            if (year === now.getFullYear()) {
              if (day === now.getDate()) {
                let hour = date.getHours()
                if (hour < 10) hour = '0' + hour
                let minute = date.getMinutes()
                if (minute < 10) minute = '0' + minute
                result.time = hour + ':' + minute
              } else {
                result.time = (month + 1) + '-' + day
              }
            } else {
              result.time = year + '-' + (month + 1) + '-' + day
            }
            console.log(this.result)
          }
          result.message = this.limitedString(result.message, 7)
          // console.log(result)
          return result
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
        },
        limitedString (string, limit) {
          let str = ''
          if (string.length > limit && limit >= 0) {
            str = string.slice(0, limit) + '...'
            return str
          }
          return string
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

  .unactiveChat:hover {
    background-color: #f0f0f0;
  }

  .avatar {
    margin: 7px 15px;
    height: 66px;
    width: 65px;
  }

  .nicknameItem {
    margin: 7px 0;
  }

  .title {
    margin-top: 5px;
    overflow: hidden;
  }

  .info {
    color: black;
    font-weight: 700;
    font-size: 20px;
  }

  .detail {
    font-size: 90%;
    color: gray;
    margin-top: 10px;
  }
  
  .message {
    overflow: hidden;
  }
  
  .time {
    text-align: right;
    /* font-size: 80%; */
    color: gray;
  }


  .activeChat {
    background-color: #e0e0e0;
  }
</style>
