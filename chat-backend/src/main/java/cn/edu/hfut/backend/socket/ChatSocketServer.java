package cn.edu.hfut.backend.socket;

import cn.edu.hfut.backend.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/chatSocket")
@Component
public class ChatSocketServer {

    static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        ChatSocketServer.userService = userService;
    }

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<ChatSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private Integer userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        Map<String, List<String>> paras = session.getRequestParameterMap();
        this.userId = Integer.parseInt(paras.get("token").get(0));
        System.out.println(userId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        System.out.println("logout");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("message:" + message);
//        //群发消息
//        for (ChatSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
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
