package nmid.smarthouse.framework.ucenter.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.framework.model.response.ResponseResult;
import nmid.smarthouse.framework.model.response.ResultCode;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/16 11:34 AM
 */
@Data
@ToString
@NoArgsConstructor
public class LoginResult extends ResponseResult {
    public LoginResult(ResultCode resultCode, User user) {
        super(resultCode);
        this.user = user;
    }

    private User user;
}
