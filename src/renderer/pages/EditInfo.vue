<template>
    <el-row id="main" justify="center" type="flex">
        <el-col :span="20" id="main_frame">
            <div id="avatar">
                    
                    <!-- action="https://jsonplaceholder.typicode.com/posts/" -->
                <el-upload
                    class="avatar-uploader"
                    :action="uploadUrl"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <el-image fit="fill" v-if="userInfo.avatar" :src=userInfo.avatar></el-image>
                    <!-- <img v-if="imageUrl" :src="imageUrl" class="avatar"> -->
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </div>
            <el-form :model="userInfo">
                <div id="info_text">
                    <br />
                    <el-row type="flex" justify="center" style="height: 50px;">
                        <el-form-item label="账号：" prop="nickname" label-width="60px">
                            <el-input v-model="userInfo.account" size="small" class="input_width" disabled></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row type="flex" justify="center" style="height: 50px;">
                        <el-form-item label="昵称：" prop="nickname" label-width="60px">
                            <el-input v-model="userInfo.nickname" size="small"  class="input_width"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row type="flex" justify="center" style="height: 50px;">
                        <el-form-item label="邮箱：" prop="email" label-width="60px">
                            <el-input v-model="userInfo.email" size="small"  class="input_width" disabled></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row type="flex" justify="center" style="height: 50px;">
                        <el-form-item label="性别：" prop="gender" label-width="60px">
                            <el-input v-model="userInfo.gender" size="small"  class="input_width"></el-input>
                        </el-form-item>
                    </el-row>
                    <el-row type="flex" justify="center" style="height: 50px;">
                        <el-form-item label="生日：" prop="birthday" label-width="100px">
                            <el-date-picker
                                    v-model="userInfo.birthday"
                                    type="date"
                                    placeholder="选择日期"
                                    size="small">
                            </el-date-picker>
                        </el-form-item>
                    </el-row>
                    <el-row type="flex" justify="center" style="height: 50px;">
                      <el-button type="primary" @click="save">保存</el-button>
                      <el-button @click="goBack">返回</el-button>
                    </el-row>
                </div>
            </el-form>
        </el-col>
    </el-row>
</template>
<script>
    import globalConfig from '../config/globalConfig'
    import userUrl from '../constant/url/userUrl'
    const ipc = require('electron').ipcRenderer
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
          },
          uploadUrl: '',
          account: ''
        }
      },
      methods: {
        handleAvatarSuccess (res, file) {
          console.log(res)
          let url = res.data
          console.log('新头像' + url)
          this.axios.post(userUrl.editAvatar, {
            avatar: url
          }).then(res => {
            console.log(res)
            this.userInfo.avatar = url
            ipc.send('updateAvatar', {url})
            this.$message({
              message: '成功修改头像',
              type: 'success'
            })
          })
        },
        beforeAvatarUpload (file) {
          const isJPG = file.type === 'image/jpeg'
          const isPNG = file.type === 'image/png'
          const isLt2M = file.size / 1024 / 1024 < 2

          if (!isJPG && !isPNG) {
            this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!')
          }
          if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!')
          }
          return (isJPG || isPNG) && isLt2M
        },
        save: function () {
          this.axios.post(userUrl.editProfile, {
            nickname: this.userInfo.nickname,
            gender: this.userInfo.gender === '男' ? 1 : 2,
            birthday: this.userInfo.birthday
          }).then(data => {
            // eslint-disable-next-line no-unused-expressions
            this.userInfo.gender = this.userInfo.gender === '男' ? 1 : 2
            localStorage.setItem('si_account', JSON.stringify(this.userInfo))
            console.log(this.userInfo)
            this.$message({
              message: '修改成功',
              type: 'success'
            })
            this.$router.push('/selfInfo')
          })
        },
        goBack: function () {
          this.$router.push('/selfInfo')
        }
      },
      created: function () {
        this.userInfo = JSON.parse(localStorage.getItem('si_account'))
        // console.log(this.userInfo)
        this.userInfo.gender = this.userInfo.gender === 1 ? '男' : '女'
        this.uploadUrl = globalConfig.host + userUrl.uploadFile
      },
      mounted: function () {
      }
    }
</script>

<style scoped>
    #main {
        width: 100%;
        height: 100%;
        /* height: 523px; */
        text-align: center;
        padding: 40px 0 40px 0;
        border: #d0d3d9 solid 1px;
    }
    #main_frame {
        /* margin: 0 auto; */
        /* width: 350px; */
        border: 2px solid deepskyblue;
        border-radius: 5px;
        background-color: azure;
    }
    #avatar {
        height: 130px;
        border-bottom: 2px solid deepskyblue;
        padding-top: 20px;
    }
    #info_text {
        height: 315px;
    }
    .tag {
        text-align: right;
        color: #999999;
    }
    .tag_content {
        text-align: left;
        color: #000;
    }
    .input_width {
        width: 180px;
    }
    .el-image {
        width: 100px;
        height: 100px;
        border: 2px solid deepskyblue;
        border-radius: 50%;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    #info_text > div:nth-child(8) > button {
        height: 35px;
        width: 100px;
        margin-top: 10px;
    }
</style>
