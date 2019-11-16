<template>
  <el-container>
    <el-header>FindPassword</el-header>
    <el-row id="header_bottom_row"></el-row>
    <el-main>
      <el-row type="flex" justify="center">
        <el-form :model="form" ref="form" :rules="rules" label-position="right" label-width="80px">
          <el-form-item label="邮箱" prop="email">
            <el-input type="email" placeholder="请输入您的注册邮箱"
                      v-model="form.email" class="input_width"></el-input>
          </el-form-item>
          <el-form-item label="新密码"  prop="password">
            <el-input type="password" placeholder="请输入您的新密码"
                      v-model="form.password" class="input_width"></el-input>
          </el-form-item>
          <el-form-item label="确认密码"  prop="password2">
            <el-input type="password" placeholder="请再次输入您的密码"
                      v-model="form.password2" class="input_width"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="verifyCode">
            <el-row>
              <el-col :span=16>
                <el-input type="text" placeholder="请输入验证码"
                          v-model="form.captcha" class="captchaInput"></el-input>
              </el-col>
              <el-col :span=8>
                <el-button type="primary" @click="sendCaptcha" :disabled="sendCaptchaCoolDown"
                           class="captchaButton" plain>获取验证码</el-button>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item>
            <el-col :offset=6>
              <el-button type="primary" id="bt_submit"
                         @click="submitForm('form')">确认</el-button>
            </el-col>
          </el-form-item>
        </el-form>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import userUrl from '@/constant/url/userUrl'
import globalRespCode from '@/constant/code/globalRespCode'

export default {
  name: 'findPassword',
  data () {
    var validatePass2 = (rule, value, callback) => {
      if (value !== this.form.password) {
        callback(new Error('两次密码不一致'))
      }
    }
    return {
      sendCaptchaCoolDown: false,
      form: {
        email: 'prassiacaesar@163.com',
        password: '666',
        password2: '667',
        captcha: 'mbti'
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
        verifyCode: [
          {required: true, message: '邮箱验证码不为空', trigger: 'blur'},
          {pattern: /^([0-9a-zA-Z]){4}$/, message: '邮箱验证码组成为4位,字母和数字的组合', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
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
    submitForm (formName) {
      this.$message('尝试提交')
      this.$refs[formName].validate((valid) => {
        console.log(valid)
        if (valid) {
          this.$message({
            type: 'success',
            message: '提交成功！'
          })
        } else {
          this.$message({
            type: 'error',
            message: '提交失败！'
          })
          return false
        }
      })
    }
  }
}
</script>

<style>
  body {
    margin: 0;
    padding: 0;
  }
  #header {
    margin-top: 50px;
    height: 200px;
    text-align: center
  }
  #header_bottom_row {
    height: 40px
  }
  .form {
    width: 60vw;
    text-align: center;
  }
  .input_width {
    width: 60vw;
    margin-bottom: 10px;
    text-align: center;
  }
  #bt_submit {
    /*margin-right: 44px;*/
    width: 150px;
    margin-top: 20px;
  }
  a {
    text-decoration: none;
  }
  .captchaInput {
    width: 100%;
  }
  .captchaButton {
    width: 100%;
  }
</style>
