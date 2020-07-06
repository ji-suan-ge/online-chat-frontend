<template>
    <div
        :class="'titlebtn titlebtn-' + type"
        @click="click"
    >
      <div :class="icon + ' titlebtn-icon'"></div>
    </div>
</template>

<script>
    const {ipcRenderer: ipc} = require('electron')
    const style = {
      min: {
        right: '30px'
      },
      close: {
        right: '0px'
      }
    }
    const icons = {
      min: 'el-icon-minus',
      close: 'el-icon-close'
    }
export default {
      name: 'TitleButton',
      props: ['type'],
      computed: {
        style: function () {
          return style[this.type]
        },
        icon: function () {
          return icons[this.type]
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
        /* top: 10px; */
        width: 30px;
        height: 30px;
        -webkit-app-region: no-drag;
        /* border-radius: 50%; */
        z-index: 9999;
    }
    .titlebtn-min {
        right: 30px;
        transition: background-color 0.5s;
    }
    .titlebtn-min:hover {
        background-color: #409EFF;
    }
    .titlebtn-close {
        right: 0;
        transition: background-color 0.5s;
    }
    .titlebtn-close:hover {
        background-color: #F56C6C;
    }
    .titlebtn-icon {
        margin: 7px
    }
</style>

