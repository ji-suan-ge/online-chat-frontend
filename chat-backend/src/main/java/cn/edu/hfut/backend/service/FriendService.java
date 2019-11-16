package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.entity.User;

import java.util.List;

public interface FriendService {

    List<User> getAllFriendById(Integer id);

    void addFriend(Integer userId, Integer friendId);

    Integer getFriendIdByAccount(String account);

    User findFriend(Integer friendId, String nickname, String account);
}
