<template>
    <div id="main">
        <div id="main_frame">
            <div id="avatar">
                <el-image fit="fill" :src=userInfo.avatar></el-image>
            </div>
            <div id="info_text">
                <br />
                <el-row>
                    <el-col :span="4" :push="6" class="tag">
                        账号：
                    </el-col>
                    <el-col :span="14" :push="7" class="tag_content">
                        {{userInfo.account}}
                    </el-col>
                </el-row>
                <br />
                <el-row>
                    <el-col :span="4" :push="6" class="tag">
                        昵称：
                    </el-col>
                    <el-col :span="14" :push="7" class="tag_content">
                        {{userInfo.nickname}}
                    </el-col>
                </el-row>
                <br />
                <el-row>
                    <el-col :span="4" :push="6" class="tag">
                        邮箱：
                    </el-col>
                    <el-col :span="14" :push="7" class="tag_content">
                        {{userInfo.email}}
                    </el-col>
                </el-row>
                <br />
                <el-row>
                    <el-col :span="4" :push="6" class="tag">
                        性别：
                    </el-col>
                    <el-col :span="14" :push="7" class="tag_content">
                        {{userInfo.gender == 1 ? '男': '女'}}
                    </el-col>
                </el-row>
                <br />
                <el-row>
                    <el-col :span="4" :push="6" class="tag">
                        生日：
                    </el-col>
                    <el-col :span="14" :push="7" class="tag_content">
                        {{userInfo.birthday ? userInfo.birthday.substr(0, 10) : '未设置'}}
                    </el-col>
                </el-row>
                <br />
            </div>
        </div>
    </div>
</template>
<script>
    const ipc = require('electron').ipcRenderer
    ipc.on('sendAcc', (e, data) => {
      localStorage.setItem('fi_account', JSON.stringify(data))
    })
    export default {
      name: 'FriendInfo',
      data () {
        return {
          userInfo: {
            account: '19813622',
            nickname: '天天学习',
            gender: 1,
            avatar: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573833482292&di=e80faf82f91b22a0c575918f05b7efca&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201308%2F22%2F20130822052156_w3VjQ.jpeg',
            birthday: '2019.01.11',
            email: '126@q163.com'
          }
        }
      },
      created: function () {
        this.userInfo = JSON.parse(localStorage.getItem('fi_account'))
        console.log(this.userInfo)
      },
      mounted: function () {
      }
    }
</script>

<style scoped>
    #main {
        width: 100%;
        height: 523px;
        text-align: center;
        padding: 40px 0 0 0;
    }
    #main_frame {
        margin: 0 auto;
        width: 350px;
        border: 2px solid deepskyblue;
        border-radius: 5px;
    }
    #avatar {
        height: 150px;
        border-bottom: 2px solid deepskyblue;
        padding-top: 30px;
    }
    #info_text {
        height: 250px;

    }
    .tag {
        text-align: right;
        color: #999999;
    }
    .tag_content {
        text-align: left;
        color: #000;
    }
    .el-image {
        width: 100px;
        height: 100px;
        border: 2px solid deepskyblue;
        border-radius: 50%;
    }
</style>
