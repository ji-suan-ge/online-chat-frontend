<template>
    <el-container>
        <el-aside width="600px" class="asideColor"></el-aside>
        <el-container>
            <el-header id="header"></el-header>
            <el-main>
                <el-row>
                    <el-col :span="4" :push="4">
                        <img src="../assets/coo.png" />
                    </el-col>
                </el-row>
                <el-row type="flex" justify="center" style="margin-top: 30px;">
                    <el-form ref="loginForm" v-model="loginForm"
                             class="loginForm">
                        <el-form-item prop="credential">
                            <el-input v-model="loginForm.credential"
                                      prefix-icon="el-icon-user-solid" style="width:250px"
                                      placeholder="帐号"></el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                            <el-input v-model="loginForm.password" type="password"
                                      prefix-icon="el-icon-lock" style="width:250px"
                                      placeholder="密码">
                                <el-button slot="append" icon="el-icon-right"
                                           @click="onSubmit('loginForm')"></el-button>
                            </el-input>
                        </el-form-item>
                        <el-form-item>
                            <router-link to="/register">
                                <el-button class="operateButton" type="text" size="medium">
                                    注册帐号
                                </el-button>
                            </router-link>
                            <router-link to="/findPassword">
                                <el-button class="operateButton" type="text" size="medium">
                                    找回密码
                                </el-button>
                            </router-link>
                            <!--                            <el-button type="primary" class="submitButton"
                                                                   @click="onSubmit('loginForm')"
                                                                   style="margin-top: 10px">登录</el-button>-->
                        </el-form-item>
                    </el-form>
                </el-row>
            </el-main>
        </el-container>
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
                    credential: '',
                    password: '',
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
    body {
        margin: 0;
        padding: 0;
        font-family: "Microsoft YaHei";
    }
    .el-container {
        margin: auto;
    }
    .el-aside {
        height: 680px;
        background: #2d85f0;  /* fallback for old browsers */
        background: -webkit-linear-gradient(to right, #2d85f0, #d1c4e9);  /* Chrome 10-25, Safari 5.1-6 */
        background: linear-gradient(to right, #2d85f0, #d1c4e9); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background-size: 400%;
        background-position: 0% 100%;
        animation: gradient 5s ease-in-out infinite;
    }
    @keyframes gradient{50%{background-position:100% 0}}
    .loginForm {
        width: 50vw;
        text-align: center;
    }
    #header {
        margin-top: 20px;
        height: 180px;
        text-align: center
    }
    .submitButton {
        height: 40px;
        width: 250px;
    }
    .operateButton {
        width: 100px;
    }
</style>
