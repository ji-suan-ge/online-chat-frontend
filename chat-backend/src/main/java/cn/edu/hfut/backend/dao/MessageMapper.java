package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * from message WHERE (userId = #{userId} and friendId = #{friendId} and type = 1 ) " +
            "or (userId = #{friendId} and friendId = #{userId} and type = 1)")
    List<Message> getMessage(Integer userId, Integer friendId);

    @Insert("INSERT INTO message(userId,friendId,groupId,type,content,time) " +
            "VALUES(#{userId},#{friendId},#{groupId},#{type},#{content},#{time})")
    void sendMessage(Integer userId, Integer friendId, Integer groupId, Integer type, String content, Timestamp time);

}
