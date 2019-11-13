package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.entity.User;

import java.util.List;

public interface FriendService {

    List<User> getAllFriendById(Integer id);
}
