<template>
    <el-container>
        <el-main>
            <MessageFlow></MessageFlow>
            <MessageEdit></MessageEdit>
        </el-main>

    </el-container>
</template>

<script>
    import MessageItem from './MessageItem'
    import MessageEdit from './MessageEdit'
    import MessageFlow from './MessageFlow'

export default {
      name: 'ChatWindow',
      components: {MessageFlow, MessageEdit, MessageItem},
      data () {
        return {}
      }
}
</script>

<style scoped>
    .el-main {
        margin: 0;
        padding: 0;
    }
</style>
