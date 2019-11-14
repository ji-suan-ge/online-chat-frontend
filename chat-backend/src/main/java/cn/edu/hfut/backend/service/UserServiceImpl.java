package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.constant.LoginTypeConstant;
import cn.edu.hfut.backend.dao.UserMapper;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.exception.InvalidLoginTypeException;
import cn.edu.hfut.backend.exception.InvalidPasswordException;
import cn.edu.hfut.backend.exception.UserNotFoundException;
import cn.edu.hfut.backend.util.PasswordUtil;
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
    public void enroll(String account, String password, String email, String nickname, String avatar, Timestamp birthday, Integer gender) {
        userMapper.enroll(account, password, email, nickname, avatar, birthday, gender);
    }

    public Integer getIdByCredential(String credential, Integer type) {

        Integer Id = null;
        if (LoginTypeConstant.EMAIL.equals(type)) {
            Id = userMapper.getIdByEmail(credential);
        } else if (LoginTypeConstant.ACCOUNT.equals(type)) {
            Id = userMapper.getIdByEmail(credential);
        } else {
            throw new InvalidLoginTypeException(type);
        }
        return Id;
    }
}
