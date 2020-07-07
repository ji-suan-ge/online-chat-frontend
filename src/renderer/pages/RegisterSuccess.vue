<template>
    <body>
    <aside class="profile-card">
        <header>
            <a href="#">
                <img src="../assets/avatar.jpg" />
            </a>
            <h2>您的帐号为：{{account}}</h2>
            <h2>Welcome！</h2>
        </header>
        <div class="profile-bio">
            <p>We know the good, we apprehend it clearly, but we can't bring it to achievement.</p>
        </div>
        <el-row class="profile-social-links">
            <el-col>
                <el-button type="primary" @click="toLogin">登录</el-button>
            </el-col>
        </el-row>
    </aside>
    </body>
    <!--<el-container>
        <el-main>
            <el-row type="flex" justify="center" class="main">
                <el-row>你好，你的账号是{{account}}</el-row>
                <el-button type="primary" @click="toLogin">登录</el-button>
            </el-row>
        </el-main>
    </el-container>-->
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

<!--<style scoped>
.main {
  margin-top: 200px;
  font-size: 40px;
}
</style>-->
<style scoped>
    article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary {
        display:block;
    }
    audio,canvas,video {
        display:inline-block;
    }
    audio:not([controls]) {
        display:none;height:0;
    }
    [hidden] {
        display:none;
    }
    html
    {
        font-family:sans-serif;
        -ms-text-size-adjust:100%;
        -webkit-text-size-adjust:100%;
    }
    body {
        margin:0;
    }
    a:focus {
        outline:thin dotted;
    }
    a:active,a:hover {
        outline:0;
    }
    h1 {
        font-size:2em;margin:0.67em 0;
    }
    abbr[title] {
        border-bottom:1px dotted;
    }
    b,strong {
        font-weight:bold;
    }
    dfn {
        font-style:italic;
    }
    hr {
        -moz-box-sizing:content-box;
        box-sizing:content-box;
        height:0;}
    mark {
        background:#ff0;
        color:#000;}
    code,kbd,pre,samp {
        font-family:monospace,serif;
        font-size:1em;}
    pre {
        white-space:pre-wrap;
    }
    q {
        quotes:"\201C" "\201D" "\2018" "\2019";
    }
    small {
        font-size:80%;
    }
    sub,sup {
        font-size:75%;
        line-height:0;
        position:relative;
        vertical-align:baseline;}
    sup {
        top:-0.5em;
    }
    sub {
        bottom:-0.25em;
    }
    img {
        border:0;
    }
    svg:not(:root) {
        overflow:hidden;
    }
    figure {
        margin:0;
    }
    fieldset {
        border:1px solid #c0c0c0;margin:0 2px;padding:0.35em 0.625em 0.75em;
    }
    legend {
        border:0;padding:0;
    }
    button,input,select,textarea {
        font-family:inherit;
        font-size:100%;
        margin:0;
    }
    button,input {
        line-height:normal;
    }
    button,select {
        text-transform:none;
    }
    button,html input[type="button"],input[type="reset"],input[type="submit"] {
        -webkit-appearance:button;
        cursor:pointer;
    }
    button[disabled],html input[disabled] {
        cursor:default;
    }
    input[type="checkbox"],input[type="radio"] {
        box-sizing:border-box;
        padding:0;
    }
    input[type="search"] {
        -webkit-appearance:textfield;
        -moz-box-sizing:content-box;
        -webkit-box-sizing:content-box;
        box-sizing:content-box;}
    input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration {
        -webkit-appearance:none;
    }
    button::-moz-focus-inner,input::-moz-focus-inner {
        border:0;
        padding:0;
    }
    textarea {
        overflow:auto;
        vertical-align:top;
    }
    table {
        border-collapse:collapse;
        border-spacing:0;
    }
    body {
        background: #bcdee7 url("../assets/bg.png") no-repeat center center fixed;
        background-size: 120% auto;
        position: fixed;
        padding: 0px;
        margin: 0px;
        width: 100%;
        height: 100%;
        font: normal 14px/1.618em "Roboto", sans-serif;
        -webkit-font-smoothing: antialiased;
    }
    body:before {
        content: "";
        height: 0px;
        padding: 0px;
        border: 110em solid #313440;
        position: absolute;
        left: 50%;
        top: 100%;
        z-index: 2;
        display: block;
        -webkit-border-radius: 50%;
        border-radius: 50%;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        -webkit-animation: puff 0.5s 1.8s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, borderRadius 0.2s 2.3s linear forwards;
        animation: puff 0.5s 1.8s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, borderRadius 0.2s 2.3s linear forwards;
    }
    h1, h2 {
        font-weight: 500;
        margin: 0px 0px 5px 0px;
    }
    h1 {
        font-size: 24px;
    }
    h2 {
        font-size: 16px;
    }
    p {
        margin: 0px;
    }
    .profile-card {
        background: #FFB300;
        width: 56px;
        height: 56px;
        position: absolute;
        left: 50%;
        top: 50%;
        z-index: 2;
        overflow: hidden;
        opacity: 0;
        margin-top: 70px;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        -webkit-border-radius: 50%;
        border-radius: 50%;
        -webkit-box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16), 0px 3px 6px rgba(0, 0, 0, 0.23);
        box-shadow: 0px 3px 6px rgba(0 ,0, 0, 0.16), 0px 3px 6px rgba(0, 0, 0, 0.23);
        -webkit-animation: init 0.5s 0.2s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, moveDown 1s 0.8s cubic-bezier(0.6, -0.28, 0.735, 0.045) forwards, moveUp 1s 1.8s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards, materia 0.5s 2.7s cubic-bezier(0.86, 0, 0.07, 1) forwards;
        animation: init 0.5s 0.2s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, moveDown 1s 0.8s cubic-bezier(0.6, -0.28, 0.735, 0.045) forwards, moveUp 1s 1.8s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards, materia 0.5s 2.7s cubic-bezier(0.86, 0, 0.07, 1) forwards;
    }
    .profile-card header {
        width: 179px;
        height: 280px;
        padding: 40px 20px 30px 20px;
        display: inline-block;
        float: left;
        border-right: 2px dashed #EEEEEE;
        background: #FFFFFF;
        color: #000000;
        margin-top: 50px;
        opacity: 0;
        text-align: center;
        -webkit-animation: moveIn 1s 3.1s ease forwards;
        animation: moveIn 1s 3.1s ease forwards;
    }
    .profile-card header h1 {
        color: #FF5722;
    }
    .profile-card header a {
        display: inline-block;
        text-align: center;
        position: relative;
        margin: 25px 30px;
    }
    .profile-card header a:after {
        position: absolute;
        content: "";
        bottom: 3px;
        right: 3px;
        width: 20px;
        height: 20px;
        border: 4px solid #FFFFFF;
        -webkit-transform: scale(0);
        transform: scale(0);
        background: -webkit-linear-gradient(top, #2196F3 0%, #2196F3 50%, #FFC107 50%, #FFC107 100%);
        background: linear-gradient(#2196F3 0%, #2196F3 50%, #FFC107 50%, #FFC107 100%);
        -webkit-border-radius: 50%;
        border-radius: 50%;
        -webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
        box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
        -webkit-animation: scaleIn 0.3s 3.5s ease forwards;
        animation: scaleIn 0.3s 3.5s ease forwards;
    }
    .profile-card header a > img {
        width: 120px;
        max-width: 100%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
        -webkit-transition: -webkit-box-shadow 0.3s ease;
        transition: box-shadow 0.3s ease;
        -webkit-box-shadow: 0px 0px 0px 8px rgba(0, 0, 0, 0.06);
        box-shadow: 0px 0px 0px 8px rgba(0, 0, 0, 0.06);
    }
    .profile-card header a:hover > img {
        -webkit-box-shadow: 0px 0px 0px 12px rgba(0, 0, 0, 0.1);
        box-shadow: 0px 0px 0px 12px rgba(0, 0, 0, 0.1);
    }
    .profile-card .profile-bio {
        width: 175px;
        height: 180px;
        display: inline-block;
        float: right;
        padding: 50px 20px 30px 20px;
        background: #FFFFFF;
        color: #333333;
        margin-top: 50px;
        text-align: center;
        opacity: 0;
        -webkit-animation: moveIn 1s 3.1s ease forwards;
        animation: moveIn 1s 3.1s ease forwards;
    }
    .profile-social-links {
        width: 218px;
        display: inline-block;
        float: right;
        padding: 15px 20px;
        background: #FFFFFF;
        margin: 50px 0px 0px;
        text-align: center;
        opacity: 0;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-animation: moveIn 1s 3.1s ease forwards;
        animation: moveIn 1s 3.1s ease forwards;
    }
    .profile-social-links li {
        list-style: none;
        margin: auto;
        padding: 0px;
        width: 33.3%;
        text-align: center;
    }

    .profile-social-links li a:before {
        display: block;
        content: "";
        background: rgba(0, 0, 0, 0.3);
        position: absolute;
        left: 0px;
        top: 0px;
        width: 36px;
        height: 36px;
        opacity: 1;
        -webkit-transition: transform 0.4s ease, opacity 1s ease-out;
        transition: transform 0.4s ease, opacity 1s ease-out;
        -webkit-transform: scale3d(0, 0, 1);
        transform: scale3d(0, 0, 1);
        -webkit-border-radius: 50%;
        border-radius: 50%;
    }
    .profile-social-links li a:hover:before {
        -webkit-animation: ripple 1s ease forwards;
        animation: ripple 1s ease forwards;
    }
    .profile-social-links li a img,
    .profile-social-links li a svg {
        width: 24px;
    }

    @-webkit-keyframes init {
        0% {
            width: 0px;
            height: 0px;
        }
        100% {
            width: 56px;
            height: 56px;
            margin-top: 0px;
            opacity: 1;
        }
    }
    @keyframes init {
        0% {
            width: 0px;
            height: 0px;
        }
        100% {
            width: 56px;
            height: 56px;
            margin-top: 0px;
            opacity: 1;
        }
    }
    @-webkit-keyframes puff {
        100% {
            top: 50%;
            height: 100%;
            padding: 0px 100%;
        }
    }
    @keyframes puff {
        100% {
            top: 50%;
            height: 100%;
            padding: 0px 100%;
        }
    }
    @-webkit-keyframes borderRadius {
        100% {
            border-radius: 0px;
        }
    }
    @keyframes borderRadius {
        100% {
            border-radius: 0px;
        }
    }
    @-webkit-keyframes moveDown {
        50% {
            top: 40%;
        }
        100% {
            top: 100%;
        }
    }
    @keyframes moveDown {
        50% {
            top: 40%;
        }
        100% {
            top: 100%;
        }
    }
    @-webkit-keyframes moveUp {
        0% {
            background: #FFB300;
            top: 100%;
        }
        50% {
            top: 40%;
        }
        100% {
            top: 50%;
            background: #E0E0E0;
        }
    }
    @keyframes moveUp {
        0% {
            background: #FFB300;
            top: 100%;
        }
        50% {
            top: 40%;
        }
        100% {
            top: 50%;
            background: #E0E0E0;
        }
    }
    @-webkit-keyframes materia {
        0% {
            background: #E0E0E0;
        }
        50% {
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }
        100% {
            width: 440px;
            height: 280px;
            background: #FFFFFF;
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }
    }
    @keyframes materia {
        0% {
            background: #E0E0E0;
        }
        50% {
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }
        100% {
            width: 440px;
            height: 280px;
            background: #FFFFFF;
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }
    }
    @-webkit-keyframes moveIn {
        100% {
            opacity: 1;
            margin-top: -20px;
        }
    }
    @keyframes moveIn {
        100% {
            opacity: 1;
            margin-top: -20px;
        }
    }
    @-webkit-keyframes scaleIn {
        100% {
            transform: scale(1);
        }
    }
    @keyframes scaleIn {
        100% {
            transform: scale(1);
        }
    }
    @-webkit-keyframes ripple {
        50%, 100% {
            -webkit-transform: scale3d(1, 1, 1);
            transform: scale3d(1, 1, 1);
        }
        100% {
            opacity: 0;
        }
    }
    @keyframes ripple {
        50%, 100% {
            -webkit-transform: scale3d(1, 1, 1);
            transform: scale3d(1, 1, 1);
        }
        100% {
            opacity: 0;
        }
    }
    @media screen and (min-aspect-ratio: 4/3) {
        body {
            background-size: 100% auto;
        }
        body:before {
            width: 0px;
        }
        @keyframes puff {
            100% {
                top: 50%;
                width: 100%;
                padding-bottom: 100%;
            }
        }
    }
    @media screen and (min-height: 480px) {
        .profile-card header {
            width: auto;
            height: auto;
            padding: 30px 20px;
            display: block;
            float: none;
            border-right: none;
        }
        .profile-card .profile-bio {
            width: auto;
            height: auto;
            padding: 15px 20px 30px 20px;
            display: block;
            float: none;
        }
        .profile-social-links {
            width: 100%;
            display: block;
            float: none;
        }
        @keyframes materia {
            0% {
                background: #E0E0E0;
            }
            50% {
                -webkit-border-radius: 4px;
                border-radius: 4px;
            }
            100% {
                width: 280px;
                height: 440px;
                background: #FFFFFF;
                -webkit-border-radius: 4px;
                border-radius: 4px;
            }
        }
    }
</style>
