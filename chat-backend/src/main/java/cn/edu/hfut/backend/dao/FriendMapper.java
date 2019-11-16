package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.dao.provider.FriendProvider;
import cn.edu.hfut.backend.entity.FriendRequest;
import cn.edu.hfut.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT * from user WHERE ID in ( " +
            "SELECT friendId FROM friend WHERE friend.userId= #{userId})")
    List<User> getAllFriendById(Integer userId);

    @Insert("INSERT INTO friend(userId,friendId,state)" +
            " VALUES(#{userId},#{friendId},1)")
    void addFriend(Integer userId, Integer friendId);

    @Select("SELECT ID from user WHERE account = #{account})")
    Integer getFriendIdByAccount(String account);

    @Select("SELECT ID from user WHERE ID in ( " +
            "SELECT friendId FROM friend WHERE friend.userId= #{userId})")
    List<Integer> getAllFriendId(Integer userId);

    @Select("SELECT * from user WHERE account like concat('%',#{keyword},'%')or " +
            "nickname like concat('%',#{keyword},'%') or email like concat('%',#{keyword},'%') ")
    List<User> findFriend(String keyword);

    @Insert("INSERT INTO friendrequest(userId,friendId,content,time,state)" +
            " VALUES(#{userId},#{friendId},#{content},#{timestamp},1)")
    void addFriendRequest(Integer userId, Integer friendId, String content, Timestamp timestamp);

    @Select("select max(ID) from friendrequest")
    Integer getNewID();

    @Select("SELECT * from friendrequest WHERE ID = #{id} ")
    FriendRequest getRequest(Integer id);
}
