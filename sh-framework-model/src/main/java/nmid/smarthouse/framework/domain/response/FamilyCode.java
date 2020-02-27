package nmid.smarthouse.framework.domain.response;

import nmid.smarthouse.framework.model.response.ResultCode;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/21 9:15 PM
 */
public enum FamilyCode implements ResultCode {
    FAMILY_ADD_NOTEXITS(false, 300, "该用户不存在"),
    FAMILY_ADD_EXITS(false, 365, "该家人已存在，请互重复添加"),
    FAMILY_SELF_ERROR(false, 355, "不能添加本人"),
    FAMILY_ADD_ERROR(false, 400, "请填写用户正确信息"),
    FAMILY_VIEW_ERROR(false, 401, "不含有该家人");


    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    FamilyCode(boolean success, int code, String message) {
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
