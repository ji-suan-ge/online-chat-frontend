package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.MessageMapper;
import cn.edu.hfut.backend.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getMessage(Integer userId, Integer friendId) {
            return messageMapper.getMessage(userId,friendId);
    }
}
