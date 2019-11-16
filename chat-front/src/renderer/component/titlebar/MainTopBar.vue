<template>
    <el-menu  class="el-menu-demo" mode="horizontal">
        <el-row type="flex" justify="center">
            <el-menu-item index="1" route="{name: '/main'}">
                <i class="el-icon-chat-dot-round" style="font-size: 25px;"></i>
            </el-menu-item>
            <el-menu-item index="2" @click="displaySearchFriend" >
                <i class="el-icon-circle-plus-outline" style="font-size: 25px;"></i>
            </el-menu-item>
            <el-menu-item index="3" @click="displaySelfInfo">
                <i class="el-icon-user" style="font-size: 25px;"></i>
            </el-menu-item>
        </el-row>
    </el-menu>
</template>

<script>
  const ipc = require('electron').ipcRenderer
  export default {
    name: 'MainTopBar',
    props: ['user'],
    data () {
      return {
      }
    },
    methods: {
      displaySelfInfo () {
        ipc.send('selfInfo')
        ipc.send('getSelfAcc', this.user)
      },
      displaySearchFriend () {
        ipc.send('searchFriend')
      }
    }
  }
</script>

<style scoped>

</style>
