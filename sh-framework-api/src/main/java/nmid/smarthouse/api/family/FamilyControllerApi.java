package nmid.smarthouse.api.family;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:32 PM
 */
@Api(tags = "家人类接口", description = "添加家人、删除家人、 获得家人列表")
public interface FamilyControllerApi {

    @ApiOperation("获取家人列表")
    public String getFamilyMembers(String JSESSIONID);

    @ApiOperation("添加家人")
    public String addFamilyMember(@ApiParam("用户电话") String phone, @ApiParam("生日") String borth, String JSESSIONID);

    @ApiOperation("删除家人")
    public String delFamilyMember(@ApiParam("用户电话") String phone, String JSESSIONID);


    @ApiOperation("得到家人列表； 用于微服务调用【忽略】")
    public String getFamilyMembers2(@ApiParam("用户电话，用于微服务调用【忽略】") String phone);


}
