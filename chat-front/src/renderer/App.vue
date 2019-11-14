<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
  export default {
    name: 'chat-front'
  }
</script>

<style>
    /* CSS */
    body {
        margin: 0;
        padding: 0;
    }
    #app {
        height: 100vh;
    }
    .el-header {
        -webkit-app-region: drag;
    }
</style>
