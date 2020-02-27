package nmid.smarthouse.info;

import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.info.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * @author piwenjing
 * @description
 * @date 2020/2/17 3:13 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    UserService userService;

    //    @Test
//    public void TestLogin() {
//        String user = userService.Login("123", "123", "1");
//        System.out.println(user);
//    }
    @Test
    public void TestSms() {
        userService.sendCode("15310453249");
    }

}
