<template>
    <el-menu  class="el-menu-demo" mode="horizontal">
        <el-row type="flex" justify="center">
            <el-menu-item index="1" @click="toMain">
                <i class="el-icon-chat-round" style="font-size: 25px;"></i>
            </el-menu-item>
            <el-menu-item index="2" @click="toGroup">
                <i class="el-icon-chat-dot-round" style="font-size: 25px;"></i>
            </el-menu-item>
            <el-menu-item index="3" @click="findFriend" >
                <i class="el-icon-circle-plus-outline" style="font-size: 25px;"></i>
            </el-menu-item>
            <el-menu-item index="4" @click="showProfile">
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
      showProfile () {
        ipc.send('selfInfo')
        ipc.send('getSelfAcc', this.user)
      },
      findFriend () {
        ipc.send('searchFriend')
      },
      toMain () {
        this.$router.push({
          name: 'main-page'
        })
      },
      toGroup () {
        this.$router.push({
          name: 'group-chat'
        })
      }
    }
  }
</script>

<style scoped>
</style>