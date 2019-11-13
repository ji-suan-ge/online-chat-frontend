package cn.edu.hfut.backend.controller;


import cn.edu.hfut.backend.dto.user.EnrollReqBean;
import cn.edu.hfut.backend.dto.user.LoginReqBean;
import cn.edu.hfut.backend.dto.user.GetAllFriendReqBean;
import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.service.UserService;
import cn.edu.hfut.backend.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public Response login(@RequestBody @Valid LoginReqBean loginReqBean,
                         HttpSession httpSession) {

        String credential = loginReqBean.getCredential();
        String password = loginReqBean.getPassword();
        Integer type = loginReqBean.getType();

        User user = userService.login(credential, password, type);

        httpSession.setAttribute("user", user);
        user.setPassword(null);
        return ResultUtil.success(user);
    }

    @PostMapping("getAllFriend")
    public Response getAllFriendById(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        List<User> users= userService.getAllFriendById(userId);

        for (int i=0;i < users.size();i++)
            users.get(i).setPassword(null);
        httpSession.setAttribute("users", users);
        return ResultUtil.success(users);
    }

    @PostMapping("enroll")
    public Response enroll(@RequestBody @Valid EnrollReqBean enrollReqBean,
                          HttpSession httpSession) {

        String account = enrollReqBean.getAccount();
        String password = enrollReqBean.getPassword();
        String email = enrollReqBean.getEmail();
        String nickname = enrollReqBean.getNickname();
        String avatar = enrollReqBean.getAvatar();
        Timestamp birthday = enrollReqBean.getBirthday();
        Integer gender = enrollReqBean.getGender();

        userService.enroll(account,password,email,nickname,avatar,birthday,gender);
        return ResultUtil.success();
    }
}
