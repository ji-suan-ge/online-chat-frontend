<template>
  <el-container>
    <el-aside width="600px" class="asideColor"></el-aside>
    <el-container>
      <el-header id="header"> </el-header>
      <el-main>
        <el-row>
          <el-col :span="4" :push="4">
            <img src="../assets/coo.png" />
          </el-col>
        </el-row>
        <el-row type="flex" justify="center" style="margin-top: 30px;">
          <el-form :model="findPasswordForm" ref="fpForm" :rules="rules">
            <el-form-item prop="email">
              <el-input type="email" placeholder="注册邮箱"
                        v-model="findPasswordForm.email" class="input_width"></el-input>
            </el-form-item>
            <el-form-item prop="captcha">
              <el-input type="text" placeholder="验证码"
                        v-model="findPasswordForm.captcha" class="captchaInput"></el-input>
              <el-button type="primary" @click="sendCaptcha"
                         :disabled="sendCaptchaCoolDown" plain>获取验证码</el-button>
            </el-form-item>
            <el-form-item prop="password">
              <el-input type="password" placeholder="新密码"
                        v-model="findPasswordForm.password" class="input_width"></el-input>
            </el-form-item>
            <el-form-item prop="password2">
              <el-input type="password" placeholder="确认密码"
                        v-model="findPasswordForm.password2" class="input_width"></el-input>
            </el-form-item>
            <el-form-item>
              <el-row>
                <el-col :span="12" :push="4">
                  <el-button type="primary"  class="operateButton" @click="submitForm('fpForm')">确认</el-button>
                </el-col>
                <el-col :span="12" :push="2">
                  <el-button type="primary"  class="operateButton" @click="returnToLogin()">返回</el-button>
                </el-col>
              </el-row>
            </el-form-item>
          </el-form>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import userUrl from '@/constant/url/userUrl'
  import globalRespCode from '@/constant/code/globalRespCode'

  export default {
    name: 'findPassword',
    data () {
      var validatePass2 = (rule, value, callback) => {
        if (value !== this.findPasswordForm.password) {
          callback(new Error('两次密码不一致'))
        }
      }
      return {
        sendCaptchaCoolDown: false,
        findPasswordForm: {
          email: '',
          password: '',
          password2: '',
          captcha: ''
        },
        rules: {
          email: [
            {required: true, message: '邮箱不为空', trigger: 'blur'},
            {type: 'email', message: '邮箱格式错误，请输入有效的邮箱', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不为空', trigger: 'blur'}/*,
          {min: 8, max: 16, message: '密码长度在 8 到 16 个字符', trigger: 'blur'} */
          ],
          password2: [
            {required: true, message: '密码不为空', trigger: 'blur'}, /*
          {min: 8, max: 16, message: '密码长度在 8 到 16 个字符', trigger: 'blur'}, */
            {validator: validatePass2, trigger: 'blur'}
          ],
          captcha: [
            {required: true, message: '邮箱验证码不为空', trigger: 'blur'},
            {pattern: /^([0-9a-zA-Z]){4}$/, message: '邮箱验证码组成为4位,字母和数字的组合', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      VerifyEmail (str) {
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
        return regEmail.test(str)
      },
      sendCaptcha () {
        // 校验邮箱格式
        if (!this.VerifyEmail(this.findPasswordForm.email)) {
          this.$message({
            type: 'error',
            message: '邮箱格式不正确'
          })
          return
        }
        // 请求发送验证码
        this.axios.post(userUrl.findPasswordCaptcha, {email: this.findPasswordForm.email}).then(res => {
          console.log(res.data)
          if (res.data.code === globalRespCode.SUCCESS) {
            this.$message('验证码已发送至' + this.findPasswordForm.email)
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
          this.axios.post(userUrl.findPassword, this.findPasswordForm).then(res => {
            console.log(res.data)
            if (res.data.code === globalRespCode.SUCCESS) {
              this.$message({
                type: 'success',
                message: '提交成功！'
              })
              this.$router.push({
                name: 'login-page'
              })
            } else {
              this.$message({
                type: 'error',
                message: res.data.msg
              })
            }
          })
          // this.$router.push()
        } else {
          this.$message({
            type: 'error',
            message: '提交失败！'
          })
        }
      },
      returnToLogin () {
        this.$router.push({
          name: 'login-page'
        })
      }
    }
  }
</script>

<style scoped>
  body {
    margin: 0;
    padding: 0;
    font-family: "Microsoft YaHei";
  }
  .el-aside {
    height: 680px;
    background: #ffbc32;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #ffbc32, #ffe0b2);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #ffbc32, #ffe0b2); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    background-size: 400%;
    background-position: 0% 100%;
    animation: gradient 5s ease-in-out infinite;
  }
  @keyframes gradient{50%{background-position:100% 0}}
  #header {
    margin-top: 20px;
    height: 180px;
    text-align: center
  }
  .form {
    width: 50vw;
    text-align: center;
  }
  .input_width {
    margin-bottom: 10px;
    text-align: center;
  }
  .captchaInput {
    width: 64%;
  }
  .operateButton {
    width: 80px;
  }
</style>
