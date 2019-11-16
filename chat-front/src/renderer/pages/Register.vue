<template>
    <el-container>
        <el-header>Register.vue</el-header>
        <el-main>
            <el-row type="flex" justify="center">
                <el-form ref="RegisterForm" :model="newUser" label-width="80px" class="RegisterForm">
                    <el-form-item label="邮箱">
                        <el-input v-model="newUser.email"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" type="float">
                        <el-input v-model="newUser.captcha" class="captchaInput"></el-input>
                        <el-button type="primary" plain class="captchaButton" @click="sendCaptcha()"
                                   :disabled="sendCaptchaCoolDown">发送验证码</el-button>
                    </el-form-item>
                    <el-form-item label="昵称">
                        <el-input v-model="newUser.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="newUser.password" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码">
                        <el-input v-model="newUser.passwordOfVerification" type="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="Confirm">注册</el-button>
                    </el-form-item>
                </el-form>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
import globalRespCode from '@/constant/code/globalRespCode'
import userUrl from '@/constant/url/userUrl'

export default {
  name: 'Register',
  data () {
    return {
      newUser: {
        email: 'prassiacaesar@163.com',
        captcha: '',
        nickname: 'FlyingCoo',
        password: '666',
        passwordOfVerification: '666'
      },
      sendCaptchaCoolDown: false
    }
  },
  methods: {
    VerifyEmail () {
      // 验证邮箱
      return true
    },
    sendCaptcha () {
      // 校验邮箱格式
      if (!this.VerifyEmail()) {
        this.$message({
          type: 'error',
          message: '邮箱格式不正确'
        })
        return
      }
      // 请求发送验证码
      this.axios.post(userUrl.captcha, {email: this.newUser.email}).then(res => {
        console.log(res.data)
        if (res.data.code === globalRespCode.SUCCESS) {
          this.$message('验证码已发送至' + this.newUser.email)
          // 冻结发送验证码按钮
          this.sendCaptchaCoolDown = true
        } else if (res.data.code === globalRespCode.WRONG) {
          this.$message({
            type: 'error',
            message: '邮箱已被占用'
          })
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    Confirm () {
      if (!this.VerifyPassword()) {
        this.$message({
          type: 'error',
          message: '两次输入的密码不一致！'
        })
        return
      }
      this.Submit()
    },
    VerifyPassword () {
      return (this.password === this.passwordOfVerification)
    },
    Submit: function () {
      this.axios.post(userUrl.register, this.newUser).then(res => {
        console.log(res.data)
        if (res.data.code === globalRespCode.SUCCESS) {
          this.$message({
            type: 'success',
            message: '注册成功！'
          })
          this.$router.push({
            name: 'registerSuccess-page',
            params: {
              account: res.data.data.account
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.captchaInput{
    display: inline-block;
    width: 64%;
}
.captchaButton{
    width: 33%;
}
</style>
