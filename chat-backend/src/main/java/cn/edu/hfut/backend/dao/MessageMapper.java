package cn.edu.hfut.backend.dao;

import cn.edu.hfut.backend.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * from message WHERE (userId = #{userId} and friendId = #{friendId} and type = 1 ) " +
            "or (userId = #{friendId} and friendId = #{userId} and type = 1)")
    List<Message> selectMessage(Integer userId, Integer friendId);

    @Insert("INSERT INTO " +
            "message(userId,friendId,groupId,type,content,time, state) " +
            "VALUES(#{userId},#{friendId},#{groupId},#{type},#{content},#{time}, #{state})")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    void insertMessage(Message message);
}
