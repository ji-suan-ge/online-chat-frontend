package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dto.friend.GetPulledMessageRespBean;
import cn.edu.hfut.backend.dto.group.GetPulledGroupMessageRespBean;
import cn.edu.hfut.backend.entity.FriendRequest;
import cn.edu.hfut.backend.entity.Message;

import java.sql.Timestamp;
import java.util.List;


public interface MessageService {

    Message addMessage(Integer userId, Integer friendId, Integer groupId,
                       Integer type, String content, Timestamp timestamp, Integer messageState);

    List<Message> getNotPullMessageCount(Integer userId, Integer friendId);

    List<GetPulledMessageRespBean.FriendMessage> getAllFriendMessage(Integer userId);

    List<Message> getNotPullGroupMessage(Integer userId, Integer groupId);

    List<GetPulledGroupMessageRespBean.GroupMessage> getIsPullGroupMessage(Integer userId);

    void readAllPrivateMessage(Integer userId, Integer friendId);

    void updateFriendRequestState(Integer requestId);

    FriendRequest addFriendRequest(Integer userId, Integer friendId, String content, Timestamp timestamp);
}
