package cn.edu.hfut.backend.controller;

import cn.edu.hfut.backend.dto.friend.GetAllFriendRespBean;
import cn.edu.hfut.backend.dto.friend.GetInformationReqBean;
import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.service.FriendService;
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

    @PostMapping("getAllFriend")
    public Response getAllFriendById(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        System.out.println(userId);
        List<User> friendList = friendService.getAllFriendById(userId);
        friendList.forEach(friend -> friend.setPassword(null));

        GetAllFriendRespBean getAllFriendRespBean = new GetAllFriendRespBean(friendList);
        return ResultUtil.success(getAllFriendRespBean);
    }

    @PostMapping("getInformation")
    public Response getInformationByFriendId(@RequestBody @Valid GetInformationReqBean getInformationReqBean,
                                             HttpSession httpSession) {
        Integer id = getInformationReqBean.getId();
        User user = friendService.getInformationById(id);
        user.setPassword(null);
        return ResultUtil.success(user);
    }
}
