<template>
    <el-container>
        <el-main>
            <el-row type="flex" justify="center" class="main">
                <el-row>你好，你的账号是{{account}}</el-row>
                <el-button type="primary" @click="toLogin">登录</el-button>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
  export default {
    name: 'RegisterSuccess',
    data () {
      return {
        account: this.$route.params.account ? this.$route.params.account : '19765823'
      }
    },
    methods: {
      toLogin () {
        this.$router.push({
          name: 'login-page'
        })
      }
    }
  }
</script>

<style scoped>
.main {
  margin-top: 200px;
  font-size: 40px;
}
</style>
