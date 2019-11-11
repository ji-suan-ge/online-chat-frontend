<template>
    <el-container>
        <el-header>Header</el-header>
        <el-main>
            <el-row type="flex" justify="center">
                <el-form ref="loginForm" :model="user" label-width="80px" class="loginForm">
                    <el-form-item label="账号">
                        <el-input v-model="user.credential"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="user.password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
  import userUrl from '../constant/url/userUrl'
  import loginTypeConstant from '../constant/loginTypeConstant'
  import globalRespCode from '../constant/code/globalRespCode'

  export default {
    name: 'Login',
    data () {
      return {
        user: {
          credential: '',
          password: '',
          type: loginTypeConstant.ACCOUNT
        }
      }
    },
    methods: {
      onSubmit () {
        this.axios.post(userUrl.login, this.user).then(res => {
          console.log(res.data)
          if (res.data.code === globalRespCode.SUCCESS) {
            this.$message({
              type: 'success',
              message: '登陆成功！'
            })
            this.$store.dispatch('loginAction')
          }
        })
      }
    }
  }
</script>

<style scoped>
    .el-header {
        -webkit-app-region: drag;
    }

    .loginForm {
        width: 60vw;
        text-align: center;
    }
</style>
