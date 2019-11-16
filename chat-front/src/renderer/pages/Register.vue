<template>
    <el-container>
        <el-header>Register</el-header>
        <el-main>
            <el-row type="flex" justify="center">
                <el-form ref="registerForm" :model="registerForm" :rules="rules"
                         label-width="80px" class="RegisterForm">
                    <el-form-item label="邮箱" prop="email">
                        <el-input v-model="registerForm.email"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" prop="captcha" type="float">
                        <el-input v-model="registerForm.captcha" class="captchaInput"></el-input>
                        <el-button type="primary" plain class="captchaButton" @click="sendCaptcha()"
                                   :disabled="sendCaptchaCoolDown">发送验证码</el-button>
                    </el-form-item>
                    <el-form-item label="昵称" prop="nickname">
                        <el-input v-model="registerForm.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="registerForm.password" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="password2">
                        <el-input v-model="registerForm.password2" type="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('registerForm')">注册</el-button>
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
    var validatePass2 = (rule, value, callback) => {
      if (value !== this.findPasswordForm.password) {
        callback(new Error('两次密码不一致'))
      }
    }
    return {
      registerForm: {
        email: 'prassiacaesar@163.com',
        captcha: '',
        nickname: 'FlyingCoo',
        password: '666',
        password2: '666'
      },
      rules: {
        email: [
          {required: true, message: '邮箱不为空', trigger: 'blur'},
          {type: 'email', message: '邮箱格式错误，请输入有效的邮箱', trigger: 'blur'}
        ],
        captcha: [
          {required: true, message: '邮箱验证码不为空', trigger: 'blur'},
          {pattern: /^([0-9a-zA-Z]){4}$/, message: '邮箱验证码组成为4位,字母和数字的组合', trigger: 'blur'}
        ],
        nickname: [
          {required: true, message: '昵称不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不为空', trigger: 'blur'}/*,
          {min: 8, max: 16, message: '密码长度在 8 到 16 个字符', trigger: 'blur'} */
        ],
        password2: [
          {required: true, message: '密码不为空', trigger: 'blur'}, /*
          {min: 8, max: 16, message: '密码长度在 8 到 16 个字符', trigger: 'blur'}, */
          {validator: validatePass2, trigger: 'blur'}
        ]
      },
      sendCaptchaCoolDown: false
    }
  },
  methods: {
    VerifyEmail (str) {
      // 验证邮箱
      var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      return regEmail.test(str)
    },
    sendCaptcha () {
      // 校验邮箱格式
      if (!this.VerifyEmail(this.registerForm.email)) {
        this.$message({
          type: 'error',
          message: '邮箱格式不正确'
        })
        return
      }
      // 请求发送验证码
      this.axios.post(userUrl.registerCaptcha, {email: this.registerForm.email}).then(res => {
        console.log(res.data)
        if (res.data.code === globalRespCode.SUCCESS) {
          this.$message('验证码已发送至' + this.registerForm.email)
          // 冻结发送验证码按钮
          this.sendCaptchaCoolDown = true
        } else if (res.data.code === globalRespCode.EMAIL_REPETITION) {
          this.$message({
            type: 'error',
            message: '邮箱已被占用'
          })
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    submitForm (formName) {
      let validateResult = true
      this.$refs[formName].validate((valid) => { validateResult = valid })
      if (validateResult) {
        this.axios.post(userUrl.register, this.registerForm).then(res => {
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
      } else {
        this.$message({
          type: 'error',
          message: '提交失败！'
        })
      }
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
