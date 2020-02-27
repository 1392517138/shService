package nmid.smarthouse.data.controller;

import nmid.smarthouse.api.record.RecordControllerApi;
import nmid.smarthouse.framework.domain.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:41 PM
 */
@RestController
@RequestMapping("/usr/record")
public class UserInfoController implements RecordControllerApi {
    @Override
    public String uploadData(UserInfo userInfo, String JSESSIONID) {
        return null;
    }

    @Override
    public String viewData(String phone, String JSESSIONID) {
        return null;
    }
}
