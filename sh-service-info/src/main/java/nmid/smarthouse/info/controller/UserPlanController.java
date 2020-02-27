package nmid.smarthouse.info.controller;

import nmid.smarthouse.api.plan.PlanControllerApi;
import nmid.smarthouse.framework.domain.entity.Plan;
import nmid.smarthouse.info.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/26 3:50 PM
 */
@RestController
@RequestMapping("/usr/plan")
public class UserPlanController implements PlanControllerApi {
    @Autowired
    UserService userService;

    @Override
    @PostMapping("/addPlan")
    public String addPlan(@RequestBody Plan plan) {
        return userService.addPlan(plan);
    }

    @Override
    @GetMapping("/delPlan")
    public String delPlan(@RequestParam("id") Integer id) {
        return userService.delPlan(id);
    }

    @Override
    @GetMapping("/viewPlan")
    public String viewPlan(@RequestParam("JSESSIONID") String JSESSIONID) {
        return userService.viewPlan(JSESSIONID);
    }
}
