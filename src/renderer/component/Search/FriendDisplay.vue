<template>
    <el-col :span="6" >
        <div class="avatar">
            <img :src="avatar" class="avatar-image" @click="showProfile">
        </div>
        <!-- <div  @click="showProfile">
          <el-avatar shape="square" :size="50" :src="avatar"></el-avatar>
        </div> -->
        <br>
        <div class="info_text">
          <div class="nickname">
              {{nickname}}<br>
          </div>
          <div class="detail">
            ({{account}})
          </div>
        </div>
        <br>
        <div class="bt">
            <el-button 
              :disabled="myFriend || added"
              type="primary" 
              size="mini" 
              @click="addFriend">
              {{myFriend || added ? "已添加" : "添加"}}
            </el-button>
        </div>
    </el-col>
</template>

<script>
    import friendUrl from '../../constant/url/friendUrl'
    const ipc = require('electron').ipcRenderer
    export default {
      name: 'FriendDisplay',
      data () {
        return {
          selfAccount: '',
          added: false
        }
      },
      props: {
        userInfo: {
          type: Object,
          required: true
        },
        avatar: {
          type: String,
          required: true
        },
        nickname: {
          type: String,
          required: true
        },
        account: {
          type: String,
          required: true
        },
        id: {
          type: Number,
          required: true
        },
        friendList: {
          type: Array,
          required: true
        }
      },
      created: function () {
        this.selfAccount = localStorage.getItem('account')
        // console.log(this.friendList)
      },
      computed: {
        myFriend () {
          // console.log(this.friendList)
          for (const friend of this.friendList) {
            if (friend.id === this.id) {
              return true
            }
          }
          return false
        }
      },
      methods: {
        addFriend: function () {
          this.axios.post(friendUrl.addFriend, {
            friendId: this.id
          })
          this.added = true
          // this.friendList.push()
          ipc.send('refresh')
          this.$emit('submit')
        },
        showProfile () {
          localStorage.setItem('isSelf', false)
          ipc.send('friendInfo')
          ipc.send('getAcc', this.userInfo)
        }
      }
    }
</script>

<style scoped>
    .avatar {
        display: inline-block;
        height: 60px;
    }
    .avatar-image {
        width: 50px;
        height: 50px;
        /* border-radius: 50%; */
    }
    .info_text {
        display: inline-block;
        height: 20px;
        font-family: "Microsoft YaHei";
    }
    .detail{
      font-size: 75%;
      color: #d0d3d9;
      font-weight: bold;
    }
    .bt {
        display: inline-block;
        height: 30px;
    }
    /* * {
        margin: 0;
        padding: 0;
    } */
    /* .el-col {
        border: 1px solid deepskyblue;
        border-radius: 5px;
    } */
</style>
