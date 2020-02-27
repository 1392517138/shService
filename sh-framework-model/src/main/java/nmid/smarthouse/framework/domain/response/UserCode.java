package nmid.smarthouse.framework.domain.response;

import lombok.ToString;
import nmid.smarthouse.framework.model.response.ResultCode;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/18 10:05 PM
 */
@ToString
public enum UserCode implements ResultCode {
    USER_REGIST_NOTEXITS(false, 300, "该用户不存在"),
    USER_REGIST_EXITS(false, 355, "该用户已注册"),
    USER_REGIST_CODEERROR(false, 365, "验证码错误"),
    USER_LOGIN_ERROR(false, 400, "密码错误");


    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    UserCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
