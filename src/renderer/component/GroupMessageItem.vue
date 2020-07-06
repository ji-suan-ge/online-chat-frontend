<template>
<!--  <el-row :class="myMessage ? 'myMessage' : 'friendMessage'">-->
<!--    <div class="comment" v-text="message.content"></div>-->
<!--  </el-row>-->
  <el-row>
    <div class="message">
          <p v-if="message.timeString.length > 0" class="time">
            <span>{{ message.timeString }}</span>
          </p>
          <div class="main" :class="{ self: myMessage }">
            <el-avatar v-if="this.user && this.group"
                       size="large"
                       :src="myMessage ? this.user.avatar : avatar"
                       class="avatar"></el-avatar>
            <div class="text">{{ message.content }}</div>
          </div>
    </div>
  </el-row>
</template>

<script>
    export default {
      name: 'GroupMessageItem',
      props: ['message'],
      data () {
        return {
          avatar: ''
        }
      },
      computed: {
        myMessage () {
          return this.message.userId === this.user.id
        },
        group () {
          for (const group of this.groupList) {
            if (group.id === this.message.groupId) {
              return group
            }
          }
        },
        user () {
          return this.$store.getters.user
        },
        groupMemberList () {
          return this.$store.getters.groupMemberList
        },
        groupList () {
          return this.$store.getters.groupList
        }
      },
      methods: {
      },
      directives: {
        'scroll-bottom' () {
          // this.vm.$nextTick(() => {
          //   this.el.scrollTop = this.el.scrollHeight - this.el.clientHeight
          // })
        }
      },
      filters: {
        time (time) {
          time = new Date(time)
          return time.getHours() + ':' + time.getMinutes()
        }
      },
      watch: {
        groupMemberList (newValue) {
          for (let groupMember of newValue) {
            if (this.message.userId === groupMember.id) {
              this.avatar = groupMember.avatar
            }
          }
        }
      }
    }
</script>

<style lang="less" scoped>
  .message {
    padding: 5px 10px;
    .time {
      margin: 7px 0;
      text-align: center;
      > span {
        display: inline-block;
        padding: 0 18px;
        font-size: 16px;
        color: #fff;
        border-radius: 2px;
        background-color: #dcdcdc;
      }
    }
    .avatar {
      float: left;
      margin: 0 10px 0 0;
      border-radius: 3px;
    }
    .text {
      display: inline-block;
      position: relative;
      padding: 0 10px;
      max-width: ~'calc(100% - 40px)';
      min-height: 30px;
      line-height: 2.5;
      font-size: 16px;
      text-align: left;
      word-break: break-all;
      background-color: #f8f8f8;
      border-radius: 4px;
      &:before {
        content: " ";
        position: absolute;
        top: 9px;
        right: 100%;
        border: 6px solid transparent;
        border-right-color: #fafafa;
      }
    }
    .self {
      text-align: right;
      .avatar {
        float: right;
        margin: 0 0 0 10px;
      }
      .text {
        background-color: #e3f9ff;
        &:before {
          right: inherit;
          left: 100%;
          border-right-color: transparent;
          border-left-color: #e3f9ff;
        }
      }
    }
  }
</style>
