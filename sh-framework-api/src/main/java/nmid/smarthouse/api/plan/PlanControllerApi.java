package nmid.smarthouse.api.plan;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nmid.smarthouse.framework.domain.entity.Plan;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/26 3:51 PM
 */
@Api(tags = "计划类接口", description = "添加计划、删除计划、查看计划")
public interface PlanControllerApi {
    @ApiOperation("添加计划")
    public String addPlan(@ApiParam("计划") Plan plan);

    @ApiOperation("删除计划")
    public String delPlan(@ApiParam("计划Id") Integer id);

    @ApiOperation("查看所有计划")
    public String viewPlan(String JSESSIONID);
}
