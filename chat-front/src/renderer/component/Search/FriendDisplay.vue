<template>
    <el-col :span="6" type="flex" justify="center">
        <div class="avatar">
            <img :src="avatar" class="avatar_image">
        </div>
        <div class="info_text">
            {{nickname}}({{account}})
        </div>
        <div class="bt">
            <el-button v-if="!myFriend" type="primary" size="mini" @click="addFriend">添加</el-button>
        </div>
    </el-col>
</template>

<script>
    import friendUrl from '../../constant/url/friendUrl'

    export default {
      name: 'FriendDisplay',
      data () {
        return {
          selfAccount: ''
        }
      },
      props: {
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
      },
      computed: {
        myFriend () {
          console.log(this.friendList)
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
        }
      }
    }
</script>

<style scoped>
    .avatar_image {
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }
    .avatar {
        display: inline-block;
        height: 60px;
    }
    .info_text {
        display: inline-block;
        height: 20px;
    }
    .bt {
        display: inline-block;
        height: 30px;
    }
    * {
        margin: 0;
        padding: 0;
    }
    .el-col {
        border: 1px solid deepskyblue;
        border-radius: 5px;
    }
</style>
