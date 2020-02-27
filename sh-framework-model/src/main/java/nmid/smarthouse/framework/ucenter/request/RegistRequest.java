package nmid.smarthouse.framework.ucenter.request;

import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.framework.model.request.RequestData;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/16 11:48 AM
 */
public class RegistRequest extends RequestData {
    User user;
    String code;
}
