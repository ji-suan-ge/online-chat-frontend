package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM " +
            "user " +
            "WHERE user.account = #{account}")
    User getUserByAccount(String account);

    @Select("SELECT * FROM " +
            "user " +
            "WHERE user.email = #{email}")
    User getUserByEmail(String email);

    @Insert("")
    void enroll(String account, String password, String email, String nickname, String avatar, Timestamp birthday, Integer gender);
}
