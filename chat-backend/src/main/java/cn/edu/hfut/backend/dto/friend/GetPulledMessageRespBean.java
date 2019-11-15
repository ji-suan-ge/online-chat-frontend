package cn.edu.hfut.backend.dto.friend;

import cn.edu.hfut.backend.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPulledMessageRespBean {
    private List<FriendMessage> friendMessageList;

    @Data
    public static class FriendMessage {
        private Integer friendId;
        private List<Message> messageList;
    }
}
