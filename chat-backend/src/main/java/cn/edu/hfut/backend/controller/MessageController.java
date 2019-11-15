package cn.edu.hfut.backend.controller;

import cn.edu.hfut.backend.dto.friend.GetMessageReqBean;
import cn.edu.hfut.backend.dto.friend.GetRecordRespBean;
import cn.edu.hfut.backend.entity.Message;
import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.service.MessageService;
import cn.edu.hfut.backend.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("getMessage")
    public Response getRecordByFriendId(@RequestBody @Valid GetMessageReqBean getMessageReqBean,
                                        HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();
        Integer friendId = getMessageReqBean.getFriendId();

        List<Message> messageList = messageService.getMessage(userId, friendId);

        GetRecordRespBean getRecordRespBean = new GetRecordRespBean(messageList);
        return ResultUtil.success(getRecordRespBean);
    }

    @PostMapping("getNotPullMessage")
    public Response getNotReadRecordByFriendId(@RequestBody @Valid GetMessageReqBean getMessageReqBean,
                                               HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();
        Integer friendId = getMessageReqBean.getFriendId();

        List<Message> messageList = messageService.getNotPullMessage(userId, friendId);

        GetRecordRespBean getRecordRespBean = new GetRecordRespBean(messageList);
        return ResultUtil.success(getRecordRespBean);
    }

    @PostMapping("getIsPullMessage")
    public Response getIsReadRecordByFriendId(@RequestBody @Valid GetMessageReqBean getMessageReqBean,
                                               HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();
        Integer friendId = getMessageReqBean.getFriendId();

        List<Message> messageList = messageService.getIsPullMessage(userId, friendId);

        GetRecordRespBean getRecordRespBean = new GetRecordRespBean(messageList);
        return ResultUtil.success(getRecordRespBean);
    }
}
