let local = localStorage.getItem('state')
local = null
const state = local ? JSON.parse(local) : {
  chatSocket: null,
  currentChat: -1,
  user: {
    id: -1,
    password: null,
    account: '2017556',
    nickname: 'user2',
    gender: 1,
    birthday: null,
    avatar: 'http://abc.com/img.jpg',
    email: 'mail@abc.com',
    state: 1,
    lastMessageTime: '2019-11-15T16:19:29.000+0000'
  },
  friendList: [{
    id: -2,
    password: null,
    account: '2017666',
    nickname: 'user2',
    gender: 1,
    birthday: null,
    avatar: 'http://abc.com/img.jpg',
    email: 'mail@abc.com',
    state: 1,
    lastMessageTime: '2019-11-15T16:19:29.000+0000'
  }],
  online: false,
  friendMessageList: [{
    friendId: -1,
    messageList: [{
      id: -3,
      userId: -1,
      friendId: -2,
      groupId: null,
      type: 1,
      content: 'hello',
      time: '2019-11-14T11:13:51.000+0000',
      state: 2
    }]
  }],
  currentGroupChat: -3,
  groupList: [{
    id: -2,
    groupAccount: '000000',
    name: '群名',
    introduction: '群介绍',
    avatar: null,
    state: 1,
    lastMessageTime: '2019-11-15T16:19:29.000+0000'
  }],
  groupMessageList: [{
    groupId: -1,
    messageList: [{
      id: -3,
      userId: -1,
      friendId: null,
      groupId: -1,
      type: 1,
      content: 'hello',
      time: '2019-11-14T11:13:51.000+0000',
      state: 2
    }]
  }]
}
export default state
