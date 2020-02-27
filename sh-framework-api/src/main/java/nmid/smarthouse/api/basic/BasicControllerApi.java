package nmid.smarthouse.api.basic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:15 PM
 */
@Api(tags = "用户修改信息类接口", description = "用户修改名称、生日、性别、头像、密码、电话")
public interface BasicControllerApi {
    public String modName(@ApiParam("名称") String nickName, String JSESSIONID);

    public String modBorh(@ApiParam("生日") String borth, String JSESSIONID);

    public String modSex(@ApiParam("性别") String sex, String JSESSIONID);

    public String modPhoto(@ApiParam("头像") MultipartFile file, String JSESSIONID);

    public String modPwd(@ApiParam("密码") String pwd, String JSESSIONID);

    public String modPhone(@ApiParam("电话") String phone, String JSESSIONID);
}
