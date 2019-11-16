package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.entity.User;

import java.sql.Timestamp;

public interface UserService {

    User login(String credential, String password, Integer type);

    void enroll(String account, String password, String email, String nickname,
                String avatar, Timestamp birthday, Integer gender);

    String sendEmailCode(String email);

    User getByAccount(String account);

    User getByEmail(String email);

    User getById(Integer Id);

    void editProfile(Integer id, String nickname, Integer gender, Timestamp birthday);

    void editPassword(Integer userId, String plainPassword);

    void editAvatar(Integer userId, String avatar);

}
