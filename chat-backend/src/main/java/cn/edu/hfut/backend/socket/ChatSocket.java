package cn.edu.hfut.backend.socket;

import cn.edu.hfut.backend.constant.message.MessageState;
import cn.edu.hfut.backend.constant.message.MessageType;
import cn.edu.hfut.backend.constant.socket.SocketMessageType;
import cn.edu.hfut.backend.dto.socket.PrivateMessage;
import cn.edu.hfut.backend.dto.socket.SocketMessage;
import cn.edu.hfut.backend.entity.FriendRequest;
import cn.edu.hfut.backend.entity.Message;
import cn.edu.hfut.backend.service.MessageService;
import cn.edu.hfut.backend.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/chatSocket")
@Component
public class ChatSocket {

    static UserService userService;

    static MessageService messageService;

    @Autowired
    public void setUserService(UserService userService) {
        ChatSocket.userService = userService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        ChatSocket.messageService = messageService;
    }

    private static CopyOnWriteArraySet<ChatSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;
    private Integer userId;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        Map<String, List<String>> paras = session.getRequestParameterMap();
        this.userId = Integer.parseInt(paras.get("token").get(0));
        System.out.println(userId);
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("logout");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("message:" + message);
        System.out.println("userId: " + this.userId);
        SocketMessage socketMessage = JSON.parseObject(message, SocketMessage.class);
        System.out.println(socketMessage);
        Integer messageType = socketMessage.getSocketMessageType();
        String data = socketMessage.getData();

        if (SocketMessageType.PRIVATE_MESSAGE.equals(messageType)) {
            handlePrivateMessage(data);
        } else if (SocketMessageType.MARK_READ_MESSAGE.equals(messageType)) {
            handleMarkReadMessage(data);
        } else if (SocketMessageType.FRIEND_APPLY.equals(messageType)) {
            handleFriendApplyMessage(data);
        }
    }

    private void handleFriendApplyMessage(String data) {
        PrivateMessage privateMessage = JSON.parseObject(data, PrivateMessage.class);
        Integer friendId = privateMessage.getFriendId();
        String content = privateMessage.getContent();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ChatSocket friendSocket = null;
        for (ChatSocket chatSocket : webSocketSet) {
            if (chatSocket.userId.equals(friendId)) {
                friendSocket = chatSocket;
            }
        }
        FriendRequest friendRequest = messageService.addFriendRequest(this.userId, friendId, content, timestamp);
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setSocketMessageType(SocketMessageType.FRIEND_APPLY);
        socketMessage.setData(JSON.toJSONString(friendRequest));
        String socketMessageString = JSON.toJSONString(socketMessage);
        if (friendSocket != null) {
            friendSocket.session.getAsyncRemote().sendText(socketMessageString);
        }
        session.getAsyncRemote().sendText(socketMessageString);
    }

    private void handleMarkReadMessage(String data) {
        Integer friendId = JSON.parseObject(data, int.class);
        messageService.readAllPrivateMessage(userId, friendId);
    }

    private void handlePrivateMessage(String data) {
        PrivateMessage privateMessage = JSON.parseObject(data, PrivateMessage.class);
        Integer friendId = privateMessage.getFriendId();
        String content = privateMessage.getContent();
        Integer messageState = MessageState.NEW_MESSAGE;
        Integer messageType = MessageType.PRIVATE_MESSAGE;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ChatSocket friendSocket = null;
        for (ChatSocket chatSocket : webSocketSet) {
            if (chatSocket.userId.equals(friendId)) {
                friendSocket = chatSocket;
            }
        }

        Message message = messageService.addMessage(this.userId, friendId, null, messageType,
                content, timestamp, messageState);
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setData(JSON.toJSONString(message));
        socketMessage.setSocketMessageType(SocketMessageType.PRIVATE_MESSAGE);
        String socketMessageString = JSON.toJSONString(socketMessage);

        if (friendSocket != null) {
            friendSocket.session.getAsyncRemote().sendText(socketMessageString);
        }
        session.getAsyncRemote().sendText(socketMessageString);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

//    /**
//     * 群发自定义消息
//     */
//    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
////        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
//        for (ChatSocketServer item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if (sid == null) {
//                    item.sendMessage(message);
//                } else if (item.sid.equals(sid)) {
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }
}
