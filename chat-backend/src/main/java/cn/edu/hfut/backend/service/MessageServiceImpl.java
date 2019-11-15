package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.MessageMapper;
import cn.edu.hfut.backend.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getMessage(Integer userId, Integer friendId) {
        return messageMapper.selectMessage(userId, friendId);
    }

    @Override
    public Message addMessage(Integer userId, Integer friendId, Integer groupId,
                           Integer type, String content, Timestamp timestamp, Integer messageState) {
        Message message = new Message(null, userId, friendId, groupId, type, content, timestamp, messageState);
        messageMapper.insertMessage(message);
        return message;
    }

    @Override
    public List<Message> getNotPullMessage(Integer userId, Integer friendId) {
        List<Message> messageList = messageMapper.selectNotPullMessage(userId, friendId);
        messageMapper.updateMessage(userId,friendId);
        return messageList;
    }

    @Override
    public List<Message> getIsPullMessage(Integer userId, Integer friendId) {
        List<Message> messageList = messageMapper.selectIsPullMessage(userId, friendId);
        messageMapper.updateMessage(userId,friendId);
        return messageList;
    }

}
