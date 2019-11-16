package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.FriendMapper;
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
    public void addFriend(Integer userId, Integer friendId) {
        friendMapper.addFriend(userId, friendId);
        friendMapper.addFriend(friendId, userId);
    }

    @Override
    public Integer getFriendIdByAccount(String account) {
        return friendMapper.getFriendIdByAccount(account);
    }

    @Override
    public List<User> findFriend(String keyword) {
        return friendMapper.findFriend(keyword);
    }

}
