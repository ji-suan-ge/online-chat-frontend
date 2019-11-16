package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.dao.provider.UserProvider;
import cn.edu.hfut.backend.entity.User;
import org.apache.ibatis.annotations.*;

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

    @Insert("INSERT INTO `user`(account,`password`,nickname,gender,birthday,avatar,email,state) " +
            "VALUES(#{account},#{password},#{nickname},#{gender}," +
            "#{birthday},#{avatar},#{email},1)")
    void enroll(String account, String password, String email,
                String nickname, String avatar, Timestamp birthday, Integer gender);

    @Select("SELECT ID FROM " +
            "user " +
            "WHERE user.email= #{email}")
    Integer getIdByEmail(String email);

    @Select("SELECT ID FROM " +
            "user " +
            "WHERE user.account= #{account}")
    Integer getIdByAccount(String account);

    @Select("SELECT * FROM " +
            "user " +
            "WHERE user.account= #{account}")
    User getByAccount(String account);

    @Select("SELECT * FROM " +
            "user " +
            "WHERE user.email= #{email}")
    User getByEmail(String email);

    @Select("SELECT * FROM " +
            "user " +
            "WHERE user.ID= #{Id}")
    User getUserById(Integer id);

    @UpdateProvider(type = UserProvider.class, method = "updateById")
    void updateById(Integer id, String nickname, Integer gender, Timestamp birthday);

    @Update("update user set password = #{password} where id = #{userId}")
    void updatePasswordById(Integer userId, String password);

    @Update("update user set avatar = #{avatar} where id = #{userId}")
    void editAvatar(Integer userId, String avatar);
}

