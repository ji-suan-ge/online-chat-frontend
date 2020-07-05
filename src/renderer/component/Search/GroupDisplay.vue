<template>
    <el-col :span="6">
        <!-- <div class="avatar">
            <img :src="avatar" class="avatar_image" @click="showProfile">
        </div> -->
        <el-avatar shape="square" :size="50" :src="avatar" @click="showProfile"></el-avatar>
        <br>
        <div class="info_text">
          <div class="name">
              {{groupName}}<br>
          </div>
          <div class="detail">
            ({{account}})
          </div>
        </div>
        <br>
        <div class="bt">
            <el-button 
              :disabled="myGroup"
              type="primary" 
              size="mini" 
              @click="joinGroup">
              {{myGroup ? "已加入" : "申请加入"}}
            </el-button>
        </div>
    </el-col>
</template>

<script>
    import groupUrl from '@/constant/url/groupUrl'
    const ipc = require('electron').ipcRenderer
    export default {
      name: 'GroupDisplay',
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
        groupInfo: {
          tyep: Object,
          required: true
        },
        groupName: {
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
        groupList: {
          type: Array,
          required: true
        }
      },
      created: function () {
        this.selfAccount = localStorage.getItem('account')
      },
      computed: {
        myGroup () {
          // console.log(this.friendList)
          for (const group of this.groupList) {
            if (group.id === this.id) {
              return true
            }
          }
          return false
        }
      },
      methods: {
        joinGroup: function () {
          this.axios.post(groupUrl.joinGroup, {
            groupId: this.id
          })
          ipc.send('refresh')
          this.$emit('submit')
        },
        showProfile () {
          ipc.send('groupInfo')
          ipc.send('getAcc', this.groupInfo)
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
</style>
