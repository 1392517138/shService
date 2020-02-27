package nmid.smarthouse.info.controller;

import nmid.smarthouse.api.basic.BasicControllerApi;
import nmid.smarthouse.info.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:14 PM
 */
@RestController
@RequestMapping("usr/basic")
public class UserBasicController implements BasicControllerApi {

    @Autowired
    UserService userService;

    @Override
    @GetMapping("/modName")
    public String modName(@RequestParam("nickName") String nickName, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.modName(nickName, JSESSIONID);
    }

    @Override
    @GetMapping("/modBorth")
    public String modBorh(@RequestParam("borth") String borth, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.modBorth(borth, JSESSIONID);
    }

    @Override
    @GetMapping("/modSex")
    public String modSex(@RequestParam("sex") String sex, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.modSex(sex, JSESSIONID);
    }

    @Override
    @PostMapping("/modPhoto")
    public String modPhoto(MultipartFile file, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.modPhoto(file, JSESSIONID);
    }

    @Override
    @GetMapping("/modPwd")
    public String modPwd(@RequestParam("pwd") String pwd, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.modPwd(pwd, JSESSIONID);
    }

    @Override
    @GetMapping("/modPhone")
    public String modPhone(@RequestParam(" phone") String phone, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.modPhone(phone, JSESSIONID);
    }
}
