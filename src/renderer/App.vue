<template>
    <div id="app">
        <div id="main_title_bar">
            <TitleButton type="min"/>
            <TitleButton type="close"/>
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
        padding: 0;
        margin: 0;
        border: 0;
        -webkit-app-region: no-drag;
    }
    #app {
        /* border-radius: 10px; */
        background: white;
        height: 100vh;
    }
    ::-webkit-scrollbar {
      /*滚动条整体样式*/
      width : 10px;  /*高宽分别对应横竖滚动条的尺寸*/
      height: 1px;
    }
    ::-webkit-scrollbar-thumb {
      /*滚动条里面小方块*/
      border-radius: 10px;
      box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
      background: #C0C4CC;
    }
    ::-webkit-scrollbar-track {
      /*滚动条里面轨道*/
      box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
      border-radius: 10px;
      background: #ededed;
    }

    #main_title_bar {
        position: absolute;
        width: 100%;
        height: 30px;
        -webkit-app-region: drag;
    }
</style>
