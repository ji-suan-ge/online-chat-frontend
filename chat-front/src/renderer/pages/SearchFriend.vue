<template>
    <el-container class="">
        <el-header height="30px" style="background-color: cornflowerblue">
        </el-header>
        <el-main>
            <h1>查找好友</h1>
            <el-row type="flex" justify="center">
                <el-col :span="12">
                    <el-input v-model="keyword" placeholder="请输入好友账号/昵称"></el-input>
                </el-col>
                <el-col :span="2" :push="1">
                    <el-button type="primary" @click="getData" size="medium" class="el-icon-search" round>搜索</el-button>
                </el-col>
            </el-row>
        </el-main>
        <el-footer>
            <span v-for="(item, index) in all_user" :key="index">
                <FriendDisplay
                        :userInfo="item"
                        :avatar="item.avatar"
                        :nickname="item.nickname"
                        :account="item.account"
                        :id="item.id"
                        :friend-list="friendList">
                </FriendDisplay>
            </span>
        </el-footer>
    </el-container>
</template>
<script>
    import FriendDisplay from '../component/Search/FriendDisplay'
    import friendUrl from '../constant/url/friendUrl'
    const ipc = require('electron').ipcRenderer
    ipc.on('sendSelfAcc', (e, data) => {
      localStorage.setItem('account', data.account)
    })
    export default {
      name: 'SearchFriend',
      data () {
        return {
          keyword: '',
          all_user: [],
          friendList: []
        }
      },
      methods: {
        getData () {
          this.axios.post(friendUrl.findFriend, {
            keyword: this.keyword
          }).then(res => {
            this.all_user = res.data.data.friendList
            console.log(res)
          })
        },
        getFriendList () {
          this.axios.post(friendUrl.getList).then(res => {
            const data = res.data
            console.log(data)
            this.friendList = data.data.friendList
          })
        }
      },
      components: {FriendDisplay},
      created () {
        this.getFriendList()
      }
    }
</script>

<style scoped>
    * {
        padding: 0;
        margin: 0;
    }
    .el-header {
        text-align: center;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
    }
    .el-footer {
        text-align: center;
        height: 300px;
        border-radius: 10px;
    }
    .el-main {
        border-bottom: 1px solid darkgray;
        text-align: center;
        line-height: 60px;
    }
</style>
