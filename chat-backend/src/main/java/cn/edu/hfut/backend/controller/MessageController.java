package cn.edu.hfut.backend.controller;

import cn.edu.hfut.backend.dto.friend.GetPulledMessageRespBean;
import cn.edu.hfut.backend.dto.friend.GetRecordRespBean;
import cn.edu.hfut.backend.dto.group.GetGroupMessageReqBean;
import cn.edu.hfut.backend.dto.group.GetPulledGroupMessageRespBean;
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

    @PostMapping("getAllFriendMessage")
    public Response getAllFriendMessage(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        List<GetPulledMessageRespBean.FriendMessage> messageList =
                messageService.getAllFriendMessage(userId);

        GetPulledMessageRespBean getPulledMessageRespBean = new GetPulledMessageRespBean(messageList);
        return ResultUtil.success(getPulledMessageRespBean);
    }

    @PostMapping("getNotPullGroupMessage")
    public Response getNotReadRecordByGroupId(@RequestBody @Valid GetGroupMessageReqBean getGroupMessageReqBean,
                                              HttpSession httpSession) {
        Integer groupId = getGroupMessageReqBean.getGroupId();
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        List<Message> messageList = messageService.getNotPullGroupMessage(userId, groupId);

        GetRecordRespBean getRecordRespBean = new GetRecordRespBean(messageList);
        return ResultUtil.success(getRecordRespBean);
    }

    @PostMapping("getIsPullGroupMessage")
    public Response getIsReadRecordByGroupId(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        List<GetPulledGroupMessageRespBean.GroupMessage> messageList = messageService.getIsPullGroupMessage(userId);

        GetPulledGroupMessageRespBean getPulledGroupMessageRespBean = new GetPulledGroupMessageRespBean(messageList);
        return ResultUtil.success(getPulledGroupMessageRespBean);
    }
}
