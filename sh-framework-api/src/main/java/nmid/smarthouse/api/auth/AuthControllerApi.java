package nmid.smarthouse.api.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.framework.model.response.ResponseResult;
import nmid.smarthouse.framework.ucenter.request.LoginRequest;
import nmid.smarthouse.framework.ucenter.request.RegistRequest;
import nmid.smarthouse.framework.ucenter.response.LoginResult;
import nmid.smarthouse.framework.ucenter.response.RegistResult;

import javax.servlet.http.HttpSession;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/16 10:58 AM
 */
@Api(tags = "用户登陆、注册、退出、发送验证码接口", description = "用户登陆、注册、退出、发送验证码接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public String login(@ApiParam("电话") String phone, @ApiParam("密码") String pwd, HttpSession httpSession);

    @ApiOperation("退出")
    public String logout(@ApiParam("JSESSIONID") String JSESSIONID);

    @ApiOperation("注册")
    public String regist(@ApiParam("用户实体") User user, String code);

    @ApiOperation("给用户发送验证码")
    public String sendCode(@ApiParam("电话") String phone);

}
