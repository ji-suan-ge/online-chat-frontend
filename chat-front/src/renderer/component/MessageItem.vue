<template>
<!--  <el-row :class="myMessage ? 'myMessage' : 'friendMessage'">-->
<!--    <div class="comment" v-text="message.content"></div>-->
<!--  </el-row>-->
  <el-row>
    <div class="message" v-scroll-bottom="session.messages">
      <ul v-if="session">
        <li v-for="item in session.messages">
          <p class="time">
            <span>{{ item.date | time }}</span>
          </p>
          <div class="main" :class="{ self: item.self }">
            <img class="avatar" width="30" height="30" :src="item.self ? user.img : session.user.img" />
            <div class="text">{{ item.content }}</div>
          </div>
        </li>
      </ul>
    </div>
  </el-row>
</template>

<script>
    export default {
      name: 'MessageItem',
      props: ['message'],
      computed: {
        myMessage () {
          return this.message.userId === this.$store.getters.user.id
        }
      }
    }
</script>

<style lang="sass" scoped>
  /*.myMessage {*/
  /*  float: right;*/
  /*  clear: both;*/
  /*}*/
  /*.friendMessage {*/
  /*  float: left;*/
  /*  clear: both;*/
  /*}*/
  /*.comment {*/
  /*  width: 150px;*/
  /*  min-height: 30px;*/
  /*  position: relative;*/
  /*  margin: 30px auto 0;*/
  /*  background: cadetblue;*/
  /*  border-radius: 5px;*/
  /*}*/
  /*.comment:after {*/
  /*  content: '';*/
  /*  width: 0;*/
  /*  height: 0;*/
  /*  position: absolute;*/
  /*  top: 5px;*/
  /*  right: -16px;*/
  /*  border: solid 8px;*/
  /*  border-color: transparent transparent transparent cadetblue;*/
  /*  font-size: 0;*/
  /*}*/




</style>