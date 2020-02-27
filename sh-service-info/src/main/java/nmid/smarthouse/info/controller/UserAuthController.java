package nmid.smarthouse.info.controller;

import nmid.smarthouse.api.auth.AuthControllerApi;
import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.info.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/17 3:01 PM
 */
@RestController
@RequestMapping("/user/auth")
public class UserAuthController implements AuthControllerApi {
    @Autowired
    UserService userService;

    @Override
    @GetMapping("/login")
    public String login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd, HttpSession httpSession) {
        return userService.Login(phone, pwd, httpSession);
    }

    @Override
    @GetMapping("/exit")
    public String logout(@RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.exit(JSESSIONID);
    }

    @Override
    @PostMapping("/regist")
    public String regist(@RequestBody User user, @RequestParam String code) {
        return userService.regist(user, code);
    }

    @Override
    @GetMapping("/sendCode")
    public String sendCode(@RequestParam("phone") String phone) {
        return userService.sendCode(phone);
    }


}
