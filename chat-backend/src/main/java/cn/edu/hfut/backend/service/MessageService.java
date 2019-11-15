package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dto.friend.GetPulledMessageRespBean;
import cn.edu.hfut.backend.entity.Message;

import java.sql.Timestamp;
import java.util.List;


public interface MessageService {

    List<Message> getMessage(Integer userId, Integer friendId);

    Message addMessage(Integer userId, Integer friendId, Integer groupId,
                    Integer type, String content, Timestamp timestamp, Integer messageState);

    List<Message> getNotPullMessage(Integer userId, Integer friendId);

    List<GetPulledMessageRespBean.FriendMessage> getIsPullMessage(Integer userId);
}
