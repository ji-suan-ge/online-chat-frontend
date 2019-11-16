<template>
    <div id="app">
        <div id="main_title_bar">
            <TitleButton type="min" class = "el-icon-minus"/>
            <TitleButton type="close" class = "el-icon-close"/>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
  import TitleButton from './component/titlebar/TitleButton'
  export default {
    name: 'chat-front',
    components: {TitleButton}
  }
</script>

<style>
    /* CSS */
    body{
        padding: 0px;
        margin: 0px;
        border: 0px;
        -webkit-app-region: no-drag;
    }
    #app {
        border-radius: 10px;
        background: white;
        height: 563px;
    }
    #main_title_bar {
        position: absolute;
        width: 100%;
        height: 30px;
        -webkit-app-region: drag;
    }
</style>
