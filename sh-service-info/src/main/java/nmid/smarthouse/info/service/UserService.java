package nmid.smarthouse.info.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import nmid.smarthouse.framework.domain.entity.Plan;
import nmid.smarthouse.framework.domain.entity.User;
import nmid.smarthouse.framework.domain.response.FamilyCode;
import nmid.smarthouse.framework.domain.response.UserCode;
import nmid.smarthouse.framework.model.response.CommonCode;
import nmid.smarthouse.info.config.RabbitmqConfig;
import nmid.smarthouse.info.dao.UserPlanRepository;
import nmid.smarthouse.info.dao.UserRepository;
import nmid.smarthouse.utils.CheckCode;
import nmid.smarthouse.utils.ModPhoto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author piwenjing
 * @description
 * @date 2020/2/17 3:06 PM
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPlanRepository userPlanRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Value("${smarthouse.mq.registRoutingKey}")
    String routingKey;

    /**
     * @Description:登陆
     * @Date: 5:42 PM 2020/2/17
     */
    public String Login(String phone, String pwd, HttpSession httpsession) {
        User user = null;
        JSONObject returnData = new JSONObject();
        String key = httpsession.getId();
        try {
            //判断是用户不存在还是密码错误
            if (userRepository.existsById(phone)) {
                user = userRepository.findByPhoneAndPwd(phone, pwd);
                //存入redis
                stringRedisTemplate.boundValueOps(key).set(JSON.toJSONString(user), 1200, TimeUnit.DAYS);
                returnData.put("msg", CommonCode.SUCCESS);
                returnData.put("user", user);
            } else {
                returnData.put("msg", UserCode.USER_REGIST_NOTEXITS);
            }
        } catch (Exception e) {
            returnData.put("msg", CommonCode.SERVER_ERROR);
            e.printStackTrace();
        }
        //放入XX
        return returnData.toJSONString();
    }

    /**
     * @Description: 退出
     * @Date: 12:22 PM 2020/2/19
     */
    public String exit(String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            stringRedisTemplate.delete(JSESSIONID);
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    /**
     * @Description: 注册2
     * @Date: 12:22 PM 2020/2/19
     */
    public String regist(User user, String code) {
        JSONObject returnData = new JSONObject();
        try {
            //判断用户已经注册
            if (userRepository.existsById(user.getPhone())) {
                returnData.put("msg", UserCode.USER_REGIST_EXITS);
            } else {
                String code2 = stringRedisTemplate.opsForValue().get(user.getPhone());
                if (code2.equals(code)) {
                    userRepository.save(user);
                    returnData.put("msg", CommonCode.SUCCESS);
                } else {
                    returnData.put("msg", UserCode.USER_REGIST_CODEERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.INVALID_PARAM);
        }
        return returnData.toJSONString();
    }

    /**
     * 注册1 给用户发送验证码
     *
     * @param phone
     * @return
     */
    public String sendCode(String phone) {
        JSONObject returnData = new JSONObject();
        try {
            String code = CheckCode.getCheckCode(5);
            //创建消息对象
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);
            //转成json串
            String jsonString = JSON.toJSONString(msg);

            //将验证码放入redis,设置5min有效
            stringRedisTemplate.boundValueOps(phone).set(code, 5, TimeUnit.MINUTES);

            //发给mq
            rabbitTemplate.convertAndSend(RabbitmqConfig.EX_ROUTING_CAPTCHA, routingKey, jsonString);
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            returnData.put("msg", CommonCode.SERVER_ERROR);
            e.printStackTrace();
        }
        return returnData.toJSONString();
    }

    /**
     * 用户修改名称、生日、性别、照片、密码、电话
     */
    public String modName(String name, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            userRepository.updateNickname(name, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    public String modSex(String sex, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            userRepository.updateSex(sex, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    public String modBorth(String borth, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            userRepository.updateBorth(borth, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    public String modPhoto(MultipartFile file, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            //上传图片
            //获得图片地址
            String photo = new ModPhoto().uploadPhoto(file, user.getPhone());
            userRepository.updatePhoto(photo, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();

    }

    public String modPwd(String pwd, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            userRepository.updatePwd(pwd, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();

    }

    public String modPhone(String phone, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            userRepository.updatePhone(phone, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    /**
     * 得到、删除、增加家人
     */
    public String getFamilyMembers(String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            String[] families = getList(user.getPhone());
            if (families == null) {
                returnData.put("familys", "");
            } else {
                List<User> family = new LinkedList<>();
                //3.查询出的单个user添加进list
                for (String familyly : families) {
                    family.add(userRepository.findByPhone(familyly));
                    returnData.put("families", family);
                }
            }
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            returnData.put("msg", CommonCode.SERVER_ERROR);
            e.printStackTrace();
        }
        return returnData.toJSONString();
    }

    private String[] getList(String phone) {
        //1.获取家人列表
        String list = userRepository.findFamilyByPhone(phone);
        if (list.equals("")) {
            return null;
        }
        String[] famillies = null;
        //2.分割list
        if (list != null) {
            famillies = list.split(";");
        } else {
            return null;
        }
        return famillies;
    }

    public String addFamilyMember(String phone, String borth, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            //判断参数是否合法
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(borth)) {
                returnData.put("msg", CommonCode.INVALID_PARAM);
                return returnData.toJSONString();
            }
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            User user1 = userRepository.findByPhone(phone);
            //判断没有该用户
            if (user1 == null) {
                returnData.put("msg", FamilyCode.FAMILY_ADD_NOTEXITS);
                return returnData.toJSONString();
            }
            //不允许添加本人
            if (phone.equals(user.getPhone())) {
                returnData.put("msg", FamilyCode.FAMILY_SELF_ERROR);
                return returnData.toJSONString();
            }
            //已有该家人
            if (userRepository.isFamily(phone, user.getPhone()) > 0) {
                returnData.put("msg", FamilyCode.FAMILY_ADD_EXITS);
                return returnData.toJSONString();
            }
            //校验家人信息
            if (user1.getBorth().equals(borth)) {
                userRepository.addFamily(userRepository.findFamilyByPhone(user.getPhone()) + phone + ";", user.getPhone());
                returnData.put("msg", CommonCode.SUCCESS);
            } else {
                returnData.put("msg", FamilyCode.FAMILY_ADD_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    public String delFamilyMember(String phone, String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            userRepository.delFamily(phone, user.getPhone());
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            returnData.put("msg", CommonCode.SERVER_ERROR);
            e.printStackTrace();
        }
        return returnData.toJSONString();
    }

    public String getFamilyMembers2(String phone) {
        String family = "";
        try {
            family = userRepository.findFamilyByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return family;
    }

    /**
     * 查看、删除、添加计划
     */
    public String addPlan(Plan plan) {
        JSONObject returnData = new JSONObject();
        try {
            userPlanRepository.save(plan);
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            returnData.put("msg", CommonCode.SERVER_ERROR);
            e.printStackTrace();
        }
        return returnData.toJSONString();
    }

    public String delPlan(int id) {
        JSONObject returnData = new JSONObject();
        try {
            userPlanRepository.deleteById(id);
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }

    public String viewPlan(String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        try {
            User user = JSON.parseObject(stringRedisTemplate.opsForValue().get(JSESSIONID), User.class);
            List<Plan> plan = userPlanRepository.findByPhone(user.getPhone());
            returnData.put("plan", plan);
            returnData.put("msg", CommonCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("msg", CommonCode.SERVER_ERROR);
        }
        return returnData.toJSONString();
    }
}