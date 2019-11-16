<template>
    <div
        class="titlebtn"
        :style="style"
        @click="click"/>
</template>

<script>
    const {ipcRenderer: ipc} = require('electron')
    const style = {
      min: {
        backgroundColor: '#5CB87A',
        right: '40px'
      },
      close: {
        backgroundColor: '#F56C6C',
        right: '15px'
      }
    }
export default {
      name: 'TitleButton',
      props: ['type'],
      computed: {
        style: function () {
          return style[this.type]
        }
      },
      methods: {
        click: function () {
          ipc.send(this.type)
        }
      }
    }
</script>

<style scoped>
    .titlebtn {
        position: absolute;
        top: 10px;
        width: 15px;
        height: 15px;
        -webkit-app-region: no-drag;
        border-radius: 50%;
        z-index: 9999;
    }
    .titlebtn:hover{
        border: 1px solid rgba(248, 242, 242, 0.6);
    }
</style>

