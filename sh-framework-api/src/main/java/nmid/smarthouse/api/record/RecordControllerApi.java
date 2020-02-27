package nmid.smarthouse.api.record;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nmid.smarthouse.framework.domain.entity.UserInfo;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:22 PM
 */
@Api(tags = "用户测得数据类接口", description = "更新测得的数据、")
public interface RecordControllerApi {

    @ApiOperation("上传用户测得的信息")
    public String uploadData(@ApiParam("用户测得的信息") UserInfo userInfo, String JSESSIONID);

    @ApiOperation("查看用户测得的数据")
    public String viewData(@ApiParam("用户手机号") String phone, String JSESSIONID);
}
