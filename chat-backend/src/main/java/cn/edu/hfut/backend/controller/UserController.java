package cn.edu.hfut.backend.controller;


import cn.edu.hfut.backend.constant.code.UserResponseCode;
import cn.edu.hfut.backend.dto.user.*;
import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.entity.User;
import cn.edu.hfut.backend.service.UserService;
import cn.edu.hfut.backend.util.PasswordUtil;
import cn.edu.hfut.backend.util.ResultUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;

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

    @PostMapping("registerCode")
    public Response sendCaptcha(@RequestBody @Valid SendCaptchaReqBean sendCaptchaReqBean,
                                HttpSession httpSession) {
        String email = sendCaptchaReqBean.getEmail();

        User user = userService.getByEmail(email);
        if (user != null) {
            return ResultUtil.error(UserResponseCode.EMAIL_REPETITION, "邮箱已存在");
        }

        String code = userService.sendEmailCode(email);
        httpSession.setAttribute("emailCode", code);

        return ResultUtil.success();
    }

    @PostMapping("findPasswordCode")
    public Response sendFindPasswordCaptcha(@RequestBody @Valid SendCaptchaReqBean sendCaptchaReqBean,
                                            HttpSession httpSession) {
        String email = sendCaptchaReqBean.getEmail();

        User user = userService.getByEmail(email);
        if (user == null) {
            return ResultUtil.error(UserResponseCode.INVALID_EMAIL, "邮箱未注册");
        }

        String code = userService.sendEmailCode(email);
        httpSession.setAttribute("emailCode", code);

        return ResultUtil.success();
    }

    @PostMapping("findPassword")
    public Response findPassword(@RequestBody @Valid FindPasswordReqBean findPasswordReqBean,
                                 HttpSession httpSession) {
        String email = findPasswordReqBean.getEmail();
        String password = findPasswordReqBean.getPassword();
        String captcha = findPasswordReqBean.getCaptcha();

        String code = (String) httpSession.getAttribute("emailCode");
        User user = userService.getByEmail(email);

        if (!code.equals(captcha)) {
            return ResultUtil.error(UserResponseCode.INVALID_CAPTCHA, "校验错误");
        } else {
            userService.editPassword(user.getId(), password);
            return ResultUtil.success();
        }
    }

    @PostMapping("resetCode")
    public Response resetCode(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        String email = user.getEmail();

        String code = userService.sendEmailCode(email);
        httpSession.setAttribute("emailCode", code);

        return ResultUtil.success();
    }

    @PostMapping("enroll")
    public Response enroll(@RequestBody @Valid EnrollReqBean enrollReqBean,
                           HttpSession httpSession) {

        String account = null;
        User user = null;
        String emailCode = (String) httpSession.getAttribute("emailCode");
        if (!emailCode.equals(enrollReqBean.getCaptcha())) {
            return ResultUtil.error(UserResponseCode.INVALID_CAPTCHA, "验证码不正确");
        }
        do {
            account = RandomStringUtils.randomNumeric(10);
            user = userService.getByAccount(account);
        } while (user != null);

        String password = PasswordUtil.encodeSha_256(enrollReqBean.getPassword());
        String email = enrollReqBean.getEmail();
        String nickname = enrollReqBean.getNickname();
        String avatar = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=296688303,2394085764&fm=26&gp=0.jpg";
        Timestamp birthday = new Timestamp(System.currentTimeMillis());
        Integer gender = enrollReqBean.getGender();

        userService.enroll(account, password, email, nickname, avatar, birthday, gender);
        EnrollRespBean enrollRespBean = new EnrollRespBean();
        enrollRespBean.setAccount(account);
        return ResultUtil.success(enrollRespBean);
    }

    @PostMapping("getById")
    public Response getById(@RequestBody @Valid GetByIdReqBean getByIdReqBean) {
        Integer id = getByIdReqBean.getId();

        User user = userService.getById(id);
        user.setPassword(null);

        GetByIdRespBean getByIdRespBean = new GetByIdRespBean(user);
        return ResultUtil.success(getByIdRespBean);
    }

    @PostMapping("editProfile")
    public Response editProfile(@RequestBody @Valid EditProfileReqBean editProfileReqBean,
                                HttpSession httpSession) {
        String nickname = editProfileReqBean.getNickname();
        Integer gender = editProfileReqBean.getGender();
        Timestamp birthday = editProfileReqBean.getBirthday();

        User user = (User) httpSession.getAttribute("user");
        Integer id = user.getId();

        userService.editProfile(id, nickname, gender, birthday);
        return ResultUtil.success();
    }

    @PostMapping("editPassword")
    public Response editPassword(@RequestBody @Valid EditPasswordReqBean editPasswordReqBean,
                                 HttpSession httpSession) {
        String plainPassword = editPasswordReqBean.getPassword();
        String emailCode = editPasswordReqBean.getEmailCode();

        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        String code = (String) httpSession.getAttribute("emailCode");

        if (!StringUtils.equals(code, emailCode)) {
            return ResultUtil.error(UserResponseCode.INVALID_CAPTCHA, "验证码不正确");
        }

        userService.editPassword(userId, plainPassword);

        return ResultUtil.success();
    }

    @PostMapping("editAvatar")
    public Response editAvatar(@RequestBody @Valid EditAvatarReqBean editAvatarReqBean,
                               HttpSession httpSession) {
        String avatar = editAvatarReqBean.getAvatar();
        User user = (User) httpSession.getAttribute("user");
        Integer userId = user.getId();

        userService.editAvatar(userId,avatar);
        return ResultUtil.success();
    }
}
