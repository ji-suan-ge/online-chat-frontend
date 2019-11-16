<template>
    <el-container>
        <el-header id="header">
        </el-header>
        <el-main>
            <el-row>
                <el-col :span="4" :push="10">
                    <img src="../assets/coo.png" />
                </el-col>
            </el-row>
            <el-row type="flex" justify="center"style="margin-top: 50px;">
                <el-form ref="loginForm" v-model="loginForm"
                         label-width="80px" class="loginForm">
                    <el-form-item label="账号" prop="credential">
                        <el-input v-model="loginForm.credential"
                                  prefix-icon="el-icon-user-solid" style="width: 250px"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="loginForm.password" type="password"
                                  prefix-icon="el-icon-lock" style="width: 250px"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-row>
                            <el-col :span="6" :push="4">
                              <router-link to="/register">注册账户</router-link>
                            </el-col>
                            <el-col :span="6" :push="8">
                                <router-link to="/findPassword">找回密码</router-link>
                            </el-col>
                        </el-row>
                        <el-button type="primary" @click="onSubmit('loginForm')">登录</el-button>
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
        loginForm: {
          credential: '123',
          password: '666',
          type: loginTypeConstant.EMAIL,
          token: '1'
        },
        rules: {
          credential: [
            {required: true, message: '账户不能为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      VerifyEmail (str) {
        // 验证邮箱
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
        return regEmail.test(str)
      },
      onSubmit: function (formName) {
        if (this.VerifyEmail(this.loginForm.credential)) {
          this.loginForm.type = loginTypeConstant.EMAIL
        } else {
          this.loginForm.type = loginTypeConstant.ACCOUNT
        }
        let validateResult = true
        // this.$ref[formName].validate((valid) => { validateResult = valid })
        if (validateResult) {
          this.axios.post(userUrl.login, this.loginForm).then(res => {
            if (res.data.code === globalRespCode.SUCCESS) {
              // this.$message({
              //   type: 'success',
              //   message: '登录成功！'
              // })
              this.loginForm.token = res.data.data.id
              this.$store.dispatch('loginAction', {
                token: this.loginForm.token,
                user: res.data.data
              })
              this.$router.push({
                name: 'main-page'
              })
            } else {
              this.$message({
                type: 'error',
                message: res.data.msg
              })
            }
          })
        } else {
          this.$message({
            type: 'error',
            message: '账号、密码不能为空！'
          })
        }
      }
    }
  }
</script>

<style scoped>
    .loginForm {
        width: 40vw;
        text-align: center;
    }
    #header {
        margin-top: 20px;
        height: 200px;
        text-align: center
    }
    .el-container {
        margin: auto auto;
    }
</style>
