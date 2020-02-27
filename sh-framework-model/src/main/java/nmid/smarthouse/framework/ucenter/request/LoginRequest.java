package nmid.smarthouse.framework.ucenter.request;

import lombok.Data;
import lombok.ToString;
import nmid.smarthouse.framework.model.request.RequestData;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/16 11:32 AM
 */
@Data
@ToString
public class LoginRequest extends RequestData {
    String username;
    String password;
    String verifycode;

}
