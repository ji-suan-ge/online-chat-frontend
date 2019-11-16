package cn.edu.hfut.backend.controller;

import cn.edu.hfut.backend.dto.friend.AddFriendReqBean;
import cn.edu.hfut.backend.dto.friend.FindFriendReqBean;
import cn.edu.hfut.backend.dto.friend.FindFriendRespBean;
import cn.edu.hfut.backend.dto.friend.GetAllFriendRespBean;
import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.service.FriendService;
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
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    MessageService messageService;

    @PostMapping("getAllFriend")
    public Response getAllFriendById(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        List<User> friendList = friendService.getAllFriendById(userId);
        friendList.forEach(friend -> {
            friend.setPassword(null);
            friend.setNewMessageNumber(
                    messageService.getNotPullMessageCount(userId, friend.getId()).size()
            );
        });


        GetAllFriendRespBean getAllFriendRespBean = new GetAllFriendRespBean(friendList);
        return ResultUtil.success(getAllFriendRespBean);
    }

    @PostMapping("addFriend")
    public Response addFriend(@RequestBody @Valid AddFriendReqBean addFriendReqBean,
                              HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();
        Integer friendId = addFriendReqBean.getFriendId();
        friendService.addFriend(userId, friendId);

        return ResultUtil.success();
    }

    @PostMapping("findFriend")
    public Response findFriend(@RequestBody @Valid FindFriendReqBean findFriendReqBean) {

        String keyword = findFriendReqBean.getKeyword();
        List<User> friendList = friendService.findFriend(keyword);
        friendList.forEach(friend -> {
            friend.setPassword(null);
        });
        FindFriendRespBean findFriendRespBean = new FindFriendRespBean(friendList);
        return ResultUtil.success(findFriendRespBean);
    }
}
