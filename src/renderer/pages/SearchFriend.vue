<template>
    <el-container class="page">
        <el-header height="30px">
        </el-header>
        <el-main>
          <el-row>
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
              <el-menu-item index="1">
                <h1 class ="option">查找好友</h1>
              </el-menu-item>
              <el-menu-item index="2">
                <h1 class ="option">查找群聊</h1>
              </el-menu-item>
              <el-menu-item index="3">
                <h1 class ="option">新建群聊</h1>
              </el-menu-item>
            </el-menu>
          </el-row>
          <el-row v-if="activeIndex !== '3'" type="flex" justify="center" class="search">
            <el-col :span="12">
                <el-input 
                  v-if="activeIndex==='1'" 
                  v-model="keyword" 
                  placeholder="请输入好友账号/昵称"
                ></el-input>
                <el-input 
                  v-if="activeIndex==='2'" 
                  v-model="keyword" 
                  placeholder="请输入群号"
                ></el-input>
            </el-col>
            <el-col :span="2" :push="1">
                <el-button type="primary" @click="getData" size="medium" class="el-icon-search search-button" round>搜索</el-button>
            </el-col>
          </el-row>
          <el-row v-if="activeIndex==='1'" :gutter="20" class="findList">
            <FriendDisplay
              v-for="(item, index) in all_user"
              :key="index"
              :userinfo="item"
              :avatar="item.avatar"
              :nickname="item.nickname"
              :account="item.account"
              :id="item.id"
              :friend-list="friendList">
            </FriendDisplay>
          </el-row>
          <el-row v-if="activeIndex==='2'" :gutter="20" class="findList">
            <GroupDisplay
              v-for="(item, index) in all_user"
              :key="index"
              :group-info="item"
              :avatar="item.avatar"
              :group-name="item.name"
              :account="item.groupAccount"
              :id="item.id"
              :group-list="groupList">
            </GroupDisplay>
          </el-row>
          <el-row v-if="notFound" justify="center">
            <el-col>
              没有找到结果
            </el-col>
          </el-row>
          <el-row v-if="activeIndex==='3'" class="create-group-form">
            <el-form ref="createGroupForm" :rules="rules" :model="createGroupForm" label-position="left" label-width="80px">
              <el-form-item label="群名" prop="name">
                <el-input v-model="createGroupForm.name"></el-input>
              </el-form-item>
              <el-form-item label="群简介" prop="introduction">
                <el-input type="textarea" v-model="createGroupForm.introduction"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="createGroupoSubmit">{{submitName}}</el-button>
              </el-form-item>
            </el-form>
          </el-row>
          <!-- <div v-if="activeIndex==='1'">
            <el-row type="flex" justify="center" class="serach">
              <el-col :span="12">
                  <el-input v-model="keyword" placeholder="请输入好友账号/昵称"></el-input>
              </el-col>
              <el-col :span="2" :push="1">
                  <el-button type="primary" @click="getFriendData" size="medium" class="el-icon-search search-button" round>搜索</el-button>
              </el-col>
            </el-row>
            <el-row :gutter="20" class="findList">
              <FriendDisplay
                v-for="(item, index) in all_user"
                :key="index"
                :userInfo="item"
                :avatar="item.avatar"
                :nickname="item.nickname"
                :account="item.account"
                :id="item.id"
                :friend-list="friendList">
              </FriendDisplay>
            </el-row>
          </div>
          <div v-if="activeIndex==='2'">
            <el-row type="flex" justify="center" class="serach">
                <el-col :span="12">
                    <el-input v-model="keyword" placeholder="请输入群名/群号"></el-input>
                </el-col>
                <el-col :span="2" :push="1">
                    <el-button type="primary" @click="getGroupData" size="medium" class="el-icon-search search-button" round>搜索</el-button>
                </el-col>
            </el-row>
            <el-row :gutter="20" class="findList">
                <GroupDisplay
                  v-for="(item, index) in all_user"
                  :key="index"
                  :userInfo="item"
                  :avatar="item.avatar"
                  :nickname="item.nickname"
                  :account="item.account"
                  :id="item.id"
                  :friend-list="friendList">
                </GroupDisplay>
            </el-row>
          </div> -->
        </el-main>
        <el-footer>

        </el-footer>
    </el-container>
</template>
<script>
    import FriendDisplay from '../component/Search/FriendDisplay'
    import GroupDisplay from '../component/Search/GroupDisplay'
    import friendUrl from '../constant/url/friendUrl'
    import groupUrl from '../constant/url/groupUrl'
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
          friendList: [],
          groupList: [],
          activeIndex: '1',
          notFound: false,
          createGroupForm: {
            name: '',
            introduction: ''
          },
          submitName: '立即创建',
          rules: {
            name: [
              { required: true, message: '请输入活动名称', trigger: 'blur' },
              { min: 0, max: 20, message: '不能超过20个字', trigger: 'blur' }
            ],
            introduction: [
              { required: true, message: '请填写群聊简介', trigger: 'blur' }
            ]
          }
        }
      },
      methods: {
        getData () {
          switch (this.activeIndex) {
            case '1':
              this.axios.post(friendUrl.findFriend, {
                keyword: this.keyword
              }).then(res => {
                this.all_user = res.data.data.friendList
                console.log(res)
                this.notFound = (this.all_user.length === 0)
              })
              break
            case '2':
              this.axios.post(groupUrl.findGroup, {
                groupAccount: this.keyword
              }).then(res => {
                this.all_user = res.data.data.groupList
                console.log(res)
                this.notFound = (this.all_user.length === 0)
              })
              break
          }
        },
        getGroupData () {
          // this.axios.post(friendUrl.findFriend, {
          //   keyword: this.keyword
          // }).then(res => {
          //   this.all_user = res.data.data.friendList
          //   console.log(res)
          // })
        },
        getFriendList () {
          this.axios.post(friendUrl.getList).then(res => {
            const data = res.data
            // console.log(data)
            this.friendList = data.data.friendList
          })
        },
        getGroupList () {
          this.axios.post(groupUrl.getList).then(res => {
            const data = res.data
            // console.log(data)
            this.groupList = data.data.groupUserList
          })
        },
        handleSelect (key, keyPath) {
          this.activeIndex = key
          this.notFound = false
          this.keyword = ''
          this.all_user = []
        },
        createGroupoSubmit () {
          this.$refs['createGroupForm'].validate((valid) => {
            if (valid) {
              this.submitName = '正在提交'
              this.axios.post(groupUrl.createGroup, {
                name: this.createGroupForm.name,
                introduction: this.createGroupForm.introduction
              }).then(res => {
                const data = res.data
                // console.log(data)
                if (data.code === '0000') {
                  this.$message({
                    message: '群聊创建成功，请在群聊列表中查看',
                    type: 'success'
                  })
                } else {
                  this.$message({
                    message: '网络请求失败',
                    type: 'failure'
                  })
                }
                this.submitName = '立即创建'
              })
            }
          })
        }
      },
      components: {FriendDisplay, GroupDisplay},
      created () {
        this.getFriendList()
        this.getGroupList()
      }
    }
</script>

<style scoped>
    * {
        padding: 0;
        margin: 0;
    }
    .page {
      border: #d0d3d9 solid 1px;
      border-radius: 7.5px;
      height: 100%;
    }
    .el-header {
      text-align: center;
      border-top-left-radius: 10px;
      border-top-right-radius: 10px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
    .el-main {
      font-family: "Microsoft YaHei";
      /* border-bottom: 1px solid darkgray; */
      text-align: center;
      /* line-height: 60px; */
    }
    .el-footer {
      text-align: center;
      height: 300px;
      border-radius: 10px;
    }
    .search {
      /* margin: 10px 0 10px 0; */
      margin-top: 10px;
    }
    .search-button {
      box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
    .findList {
      margin-top: 10px;
    }
    .create-group-form {
      margin-top: 10px;
    }
</style>
