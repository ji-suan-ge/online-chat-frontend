<template>
    <el-container>
        <el-main>
            <el-header>RegisterSuccess.vue</el-header>
            <el-row type="flex" justify="center">
                <el-row>欢迎您，用户{{account}}</el-row>
                <el-row>
                    <el-button type="primary" @click="toLogin">登录</el-button>
                </el-row>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
  export default {
    name: 'RegisterSuccess',
    data () {
      return {
        account: this.$route.params.account
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

</style>
