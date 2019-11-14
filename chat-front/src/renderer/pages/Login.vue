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
                        <el-input v-model="user.password" type="password"></el-input>
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
          credential: '123',
          password: '666',
          type: loginTypeConstant.ACCOUNT,
          token: '1'
        }
      }
    },
    methods: {
      onSubmit: function () {
        this.axios.post(userUrl.login, this.user).then(res => {
          if (res.data.code === globalRespCode.SUCCESS) {
            this.$message({
              type: 'success',
              message: '登录成功！'
            })
            this.user.token = res.data.data.id
            this.$store.dispatch('loginAction', {
              token: this.user.token,
              user: res.data.data
            })
            this.$router.push({
              name: 'main-page'
            })
          }
        })
      }
    }
  }
</script>

<style scoped>
    .el-header {
        background-color: aliceblue;
    }
    .loginForm {
        width: 60vw;
        text-align: center;
    }
</style>
