package nmid.smarthouse.data.client;

import nmid.smarthouse.framework.client.ShServiceList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/26 2:23 PM
 */
@FeignClient(value = ShServiceList.SH_SERVICE_INFO)
@Component
public interface UserSearchClient {
    //获得家人列表
    @GetMapping("/getFamilyMembers2")
    public String getFamilyMembers2(@RequestParam("phone") String phone);
}
