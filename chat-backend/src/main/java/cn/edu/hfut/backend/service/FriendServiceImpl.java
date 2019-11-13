package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.FriendMapper;
import cn.edu.hfut.backend.entity.Message;
import cn.edu.hfut.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<User> getAllFriendById(Integer userId) {
        return friendMapper.getAllFriendById(userId);
    }

    @Override
    public List<Message> getRecord(Integer userId, Integer friendId) {
        return friendMapper.getRecord(userId,friendId);
    }

    @Override
    public User getInformationById(Integer id) {
        return friendMapper.getInformationById(id);
    }
}
