package nmid.smarthouse.info_client.mq;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import nmid.smarthouse.info_client.service.RegistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/20 10:13 AM
 */
@Component
public class ConsumerRegistSms {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerRegistSms.class);
    @Autowired
    RegistService registService;

    @RabbitListener(queues = ("${smarthouse.mq.queue}"))
    public void registSms(String msg) {
        //解析消息
        Map map = JSON.parseObject(msg, Map.class);
        //得到phone
        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
        //得到code
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fjxa5GDzbNtmG1yUzNi", "bZSejJOnbHDwRCANeHT6xyddpf428T");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "健康小卫");
        request.putQueryParameter("TemplateCode", "SMS_183795941");
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
