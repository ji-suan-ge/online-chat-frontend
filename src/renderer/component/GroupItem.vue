<template>
  <el-row type="flex"
          justify="space-between"
          :class="[{'activeChat': active}, 'groupItem']"
          @click.native="changeChat">
      <el-col :span="8">
          <el-avatar size="medium" :src="group.avatar" class="avatar" @click.native="showProfile"></el-avatar>
      </el-col>
      <el-col :span="16" class="groupnameItem">
          <el-row class="groupname">{{ group.name }}</el-row>
          <el-row class="account">({{ group.groupAccount }})</el-row>
      </el-col>
      <el-badge v-if="!!newMessageNumber && newMessageNumber > 0" :value="newMessageNumber">
      </el-badge>
  </el-row>
</template>

<script>
    const ipc = require('electron').ipcRenderer
    export default {
      name: 'GroupItem',
      props: ['group', 'active'],
      data () {
        return {
          newMessageNumber: 0
        }
      },
      computed: {
        currentGroupChat () {
          return this.$store.getters.currentGroupChat
        },
        groupList () {
          return this.$store.getters.groupList
        }
      },
      methods: {
        changeChat () {
          if (this.currentGroupChat !== this.group.id) {
            this.$store.dispatch('changeCurrentGroupChatAction', this.group.id)
          }
        },
        showProfile () {
          ipc.send('groupInfo')
          ipc.send('getGroup', this.group)
        }
      },
      created () {
      },
      watch: {
        group: {
          deep: true,
          handler (nv, ov) {
            this.newMessageNumber = nv.newMessageNumber
          }
        }
      }

    }
</script>

<style scoped>
  .groupItem {
    height: 80px;
  }

  .avatar {
    margin: 7px 15px;
    height: 66px;
    width: 65px;
  }

  .groupnameItem {
    margin: 7px 0;
  }

  .groupname {
    color: black;
    font-weight: 700;
    font-size: 20px;
    margin-top: 5px;
  }

  .account {
    color: gray;
    margin-top: 10px;
  }

  .activeChat {
    background-color: #f8f8f8;
  }
</style>
