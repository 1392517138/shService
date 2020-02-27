package nmid.smarthouse.data.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import nmid.smarthouse.data.client.UserSearchClient;
import nmid.smarthouse.data.dao.UserInfoRepository;
import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.framework.domain.entity.UserInfo;
import nmid.smarthouse.framework.domain.response.FamilyCode;
import nmid.smarthouse.framework.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/23 5:03 PM
 */
@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserSearchClient userSearchClient;


    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public String uploadData(UserInfo userInfo, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            //获取原来的信息
            UserInfo userInfo1 = userInfoRepository.getOne(userInfo.getPhone());
            //身高、体重、血压、血氧、心率、测量次数分别添加上时间
            userInfo.setHeight(userInfo.getHeight() + ":" + ft.format(date) + ";");
            userInfo.setWeight(userInfo.getWeight() + ":" + ft.format(date) + ";");
            userInfo.setBtemperature(userInfo.getBtemperature() + ":" + ft2.format(date) + ";");
            userInfo.setBsugar(userInfo.getBsugar() + ":" + ft2.format(date) + ";");
            userInfo.setBoxygen(userInfo.getBoxygen() + ":" + ft2.format(date) + ";");
            userInfo.setHrate(userInfo.getHrate() + ":" + ft2.format(date) + ";");
            userInfo.setNmeasure((String.valueOf(userInfo.getNmeasure()) + 1) + ":" + ft2.format(date) + ";");
            userInfoRepository.save(userInfo);
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    public String viewData(String phone, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            /**
             *  1.从redis中取，但要实时更新redis中的数据
             */
//            if (user.getFamily().contains(phone)){
//                UserInfo one = userInfoRepository.getOne(phone);
//                returnData.put("userInfo", one);
//                returnData.put("msg", CommonCode.SUCCESS);
//            } else {
//                returnData.put("msg", CommonCode.INVALID_PARAM);
//            }
            /**
             *  2.通过Feign调用服务
             */
            //1.判断是否有该家人
            String family = userSearchClient.getFamilyMembers2(user.getPhone());
            if (family.contains(phone)) {
                //2.有则可以查看
                UserInfo one = userInfoRepository.getOne(phone);
                returnData.put("userInfo", one);
                returnData.put("msg", CommonCode.SUCCESS);
            } else {
                returnData.put("msg", FamilyCode.FAMILY_VIEW_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }
}
