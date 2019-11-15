package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT * from user WHERE ID in ( " +
            "SELECT friendId FROM friend WHERE friend.userId= #{userId})")
    List<User> getAllFriendById(Integer userId);

    @Insert("INSERT INTO friend(userId,friendId,state)"+
            " VALUES(#{userId},#{friendId},1)")
    void addFriend(Integer userId, Integer friendId);

    @Select("SELECT ID from user WHERE account = #{account})")
    Integer getFriendIdByAccount(String account);
}
