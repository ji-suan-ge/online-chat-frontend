<template>
    <div id="main">
        <div id="main_frame">
            <div id="avatar">
                <el-image fit="fill" :src=userInfo.avatar></el-image>
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
                      <el-button type="primary" size="mini" @click="save" round>保存</el-button>
                    </el-row>
                </div>
            </el-form>
        </div>
    </div>
</template>
<script>
    import userUrl from '../constant/url/userUrl'
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
          account: ''
        }
      },
      methods: {
        save: function () {
          this.axios.post(userUrl.editProfile, {
            nickname: this.userInfo.nickname,
            gender: this.userInfo.gender === '男' ? 1 : 2,
            birthday: this.userInfo.birthday
          }).then(data => {
            this.userInfo.gender === '男' ? 1 : 2
            localStorage.setItem('si_account', JSON.stringify(this.userInfo))
            this.$router.push('/selfInfo')
          })
        }
      },
      created: function () {
        this.userInfo = JSON.parse(localStorage.getItem('si_account'))
        this.userInfo.gender = this.userInfo.gender === 1 ? '男' : '女'
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
    #info_text > div:nth-child(8) > button {
        height: 35px;
        width: 100px;
        margin-top: 10px;
    }
</style>
