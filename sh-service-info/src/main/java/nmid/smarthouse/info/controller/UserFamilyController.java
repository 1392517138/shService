package nmid.smarthouse.info.controller;

import nmid.smarthouse.api.family.FamilyControllerApi;
import nmid.smarthouse.info.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:41 PM
 */
@RestController
@RequestMapping("/usr/family")
public class UserFamilyController implements FamilyControllerApi {
    @Autowired
    UserService userService;

    @Override
    @GetMapping("/getFamilyMembers")
    public String getFamilyMembers(@RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.getFamilyMembers(JSESSIONID);
    }

    @Override
    @GetMapping("/addFamilyMember")
    public String addFamilyMember(@RequestParam("phone") String phone, @RequestParam("borth") String borth,
                                  @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.addFamilyMember(phone, borth, JSESSIONID);
    }

    @Override
    @GetMapping("/delFamilyMember")
    public String delFamilyMember(@RequestParam("phone") String phone, @RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.delFamilyMember(phone, JSESSIONID);
    }

    @Override
    @GetMapping("/getFamilyMembers2")
    public String getFamilyMembers2(@RequestParam("phone") String phone) {
        return userService.getFamilyMembers2(phone);
    }

}
