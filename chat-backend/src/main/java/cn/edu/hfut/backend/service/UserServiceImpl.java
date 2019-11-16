package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.constant.LoginTypeConstant;
import cn.edu.hfut.backend.dao.UserMapper;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.exception.InvalidLoginTypeException;
import cn.edu.hfut.backend.exception.InvalidPasswordException;
import cn.edu.hfut.backend.exception.UserNotFoundException;
import cn.edu.hfut.backend.util.EmailUtil;
import cn.edu.hfut.backend.util.PasswordUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String credential, String password, Integer type) {

        User user = null;
        if (LoginTypeConstant.EMAIL.equals(type)) {
            user = userMapper.getUserByEmail(credential);
        } else if (LoginTypeConstant.ACCOUNT.equals(type)) {
            user = userMapper.getUserByAccount(credential);
        } else {
            throw new InvalidLoginTypeException(type);
        }

        if (user == null) {
            throw new UserNotFoundException(credential);
        }

        if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            throw new InvalidPasswordException(credential);
        }

        return user;
    }

    @Override
    public void enroll(String account, String password, String email, String nickname,
                       String avatar, Timestamp birthday, Integer gender) {
        userMapper.enroll(account, password, email, nickname, avatar, birthday, gender);
    }

    @Override
    public String sendEmailCode(String email) {
        String emailCode = RandomStringUtils.randomAlphanumeric(4);
        String content = "您的邮箱验证码为：" + emailCode;
        String subject = "邮箱验证码";
        EmailUtil.send(email, subject, content);
        return emailCode;
    }

    @Override
    public User getByAccount(String account) {
        User user = userMapper.getByAccount(account);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userMapper.getByEmail(email);
        return user;
    }

    @Override
    public User getById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public void editProfile(Integer id, String nickname, Integer gender, Timestamp birthday) {
        userMapper.updateById(id, nickname, gender, birthday);
    }

    @Override
    public void editPassword(Integer userId, String plainPassword) {
        String encodedPassword = PasswordUtil.encode(plainPassword);
        userMapper.updatePasswordById(userId, encodedPassword);
    }

    @Override
    public void editAvatar(Integer userId, String avatar) {
        userMapper.editAvatar(userId,avatar);
    }
}
