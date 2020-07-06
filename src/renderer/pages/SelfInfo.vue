<template>
    <el-row id="main" justify="center" type="flex">
      <el-col id="main-frame" :span="20">
        <div id="avatar">
          <el-image fit="fill" :src=userInfo.avatar></el-image>
            <!-- <input type="file" ref="myfile">
            <el-button @click="uploadAvatar" type="success" size="mini" icon="el-icon-upload2">修改头像</el-button> -->
          <h2>
            {{userInfo.nickname}}
            <el-button type="primary" icon="el-icon-edit" @click="editInfo" circle></el-button>
          </h2>
        </div>
        <div id="info_text">
            <br/>
            <el-row>
                <el-col :span="5" :push="0" class="el-icon-user tag">
                    账号：
                </el-col>
                <el-col :span="14" :push="1" class="tag_content">
                    {{userInfo.account}}
                </el-col>
            </el-row>
            <br/>
            <!-- <el-row>
                <el-col :span="4" :push="6" class="tag">
                    昵称：
                </el-col>
                <el-col :span="14" :push="7" class="tag_content">
                    {{userInfo.nickname}}
                </el-col>
            </el-row>
            <br/> -->
            <el-row>
                <el-col :span="5" :push="0" class="el-icon-message tag">
                    邮箱：
                </el-col>
                <el-col :span="14" :push="1" class="tag_content">
                    {{userInfo.email}}
                </el-col>
            </el-row>
            <br/>
            <el-row>
                <el-col :span="5" :push="0" :class="(userInfo.gender===1 ? 'el-icon-male' : 'el-icon-female') + ' tag'">
                    性别：
                </el-col>
                <el-col :span="14" :push="1" class="tag_content">
                    {{userInfo.gender==1? '男': '女'}}
                </el-col>
            </el-row>
            <br/>
            <el-row>
                <el-col :span="5" :push="0" class="el-icon-present tag">
                    生日：
                </el-col>
                <el-col :span="14" :push="1" class="tag_content">
                    {{userInfo.birthday ? userInfo.birthday.substr(0, 10) : '未设置'}}
                </el-col>
            </el-row>
            <br/>
            <el-row>
                <el-col :span="5" :push="0" class="el-icon-time tag">
                    年龄：
                </el-col>
                <el-col :span="14" :push="1" class="tag_content">
                    {{userInfo.age}}
                </el-col>
            </el-row>
            <br/>
            <el-row>
                <el-col :span="5" :push="0" class="el-icon-location-information tag">
                    家乡：
                </el-col>
                <el-col :span="14" :push="1" class="tag_content">
                    {{userInfo.place}}
                </el-col>
            </el-row>
            <br/>
        </div>
      </el-col>
    </el-row>
    <!-- <div id="main">
        <div id="main_frame">
            
        </div>
    </div> -->
</template>
<script>
  // import * as axios from 'axios'
  // import userUrl from '../constant/url/userUrl'

  const ipc = require('electron').ipcRenderer
  ipc.on('sendSelfAcc', (e, data) => {
    console.log('保存信息', data)
    localStorage.setItem('si_account', JSON.stringify(data))
  })
  export default {
    name: 'SelfInfo',
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
    methods: {
      editInfo: function () {
        this.$router.push('/editInfo')
      }/*,
      uploadF (url, params) {
        return axios({
          method: 'post',
          url: 'http://127.0.0.1:8002/uploadFile',
          data: params,
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
      },
      uploadAvatar () {
        let myfile = this.$refs.myfile
        let files = myfile.files
        let file = files[0]
        var formData = new FormData()
        formData.append('file', file)
        this.uploadF('http://127.0.0.1:8002/uploadFile', formData).then(resp => {
          if (resp) {
            let url = resp.data.data
            console.log(url)
            this.axios.post(userUrl.editAvatar, {
              avatar: url
            }).then(res => {
              console.log(res)
              this.userInfo.avatar = url
              ipc.send('updateAvatar', {url})
            })
          }
        })
      } */
    },
    created: function () {
      this.userInfo = JSON.parse(localStorage.getItem('si_account'))
      console.log('用户信息', this.userInfo)
      this.userInfo['age'] = '21'
      this.userInfo['place'] = '安徽 合肥'
      // console.log('头像' + this.userInfo.avatar)
    },
    mounted: function () {
    }
  }
</script>

<style scoped>
    body {
      background-color: transparent;
    }

    #main {
        /* margin: 2px; */
        height: 100%;
        width: 100%;
        text-align: center;
        padding: 40px 0 40px 0;
        font-family: "Microsoft YaHei";
        border: #d0d3d9 solid 1px;
        /* box-shadow: 1px 1px 2px rgba(0, 0, 0, .12), 0 0 2px rgba(0, 0, 0, .04); */
        /* border-radius: 7.5px; */
        /* background-color: deepskyblue; */
    }

    #main-frame {
        /* margin: 0 auto; */
        /* width: 350px; */
        border: 2px solid deepskyblue;
        border-radius: 5px;
        background-color: azure;
    }

    #avatar {
        height: 180px;
        border-bottom: 2px solid deepskyblue;
        padding-top: 20px;
    }

    #info_text {
        /* margin-top: 10px; */
        height: 260px;
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
