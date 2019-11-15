package cn.edu.hfut.backend.socket;

import cn.edu.hfut.backend.constant.socket.MessageTypeConstant;
import cn.edu.hfut.backend.dto.socket.PrivateMessage;
import cn.edu.hfut.backend.dto.socket.SocketMessage;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.exception.UserNotFoundException;
import cn.edu.hfut.backend.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/chatSocket")
@Component
public class ChatSocket {

    static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        ChatSocket.userService = userService;
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
        String messageType = socketMessage.getMessageType();
        String data = socketMessage.getData();

        if (MessageTypeConstant.PRIVATE_MESSAGE.equals(messageType)) {
            handlePrivateMessage(data);
        }
    }

    private void handlePrivateMessage(String data) {
        PrivateMessage privateMessage = JSON.parseObject(data, PrivateMessage.class);
        Integer friendId = privateMessage.getFriendId();
        String content = privateMessage.getContent();
//        User friend = userService.getById(friendId);
        User user = new User();
        if (user == null) {
            throw new UserNotFoundException(String.valueOf(friendId));
        }

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
