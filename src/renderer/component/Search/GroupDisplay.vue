<template>
    <el-col :span="6" type="flex" justify="center">
        <div class="avatar">
            <img :src="avatar" class="avatar_image">
        </div>
        <div class="info_text">
            {{groupname}}({{count}}人)
        </div>
        <div class="bt">
            <el-button type="primary" size="mini" @click="addFriend">添加</el-button>
        </div>
    </el-col>
</template>

<script>
    export default {
      name: 'GroupDisplay',
      data () {
        return {
          selfAccount: ''
        }
      },
      props: {
        avatar: {
          type: String,
          required: true
        },
        groupname: {
          type: String,
          required: true
        },
        count: {
          type: String,
          required: true
        }
      },
      created: function () {
        this.selfAccount = localStorage.getItem('account')
      },
      methods: {
        addGroup: function () {

        }
      }
    }
</script>

<style scoped>
    .avatar_image {
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }
    .avatar {
        display: inline-block;
        height: 60px;
    }
    .info_text {
        display: inline-block;
        height: 20px;
    }
    .bt {
        display: inline-block;
        height: 30px;
    }
    * {
        margin: 0;
        padding: 0;
    }
    .el-col {
        border: 1px solid deepskyblue;
        border-radius: 5px;
    }
</style>
