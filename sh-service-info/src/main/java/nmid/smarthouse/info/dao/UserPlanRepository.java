package nmid.smarthouse.info.dao;

import nmid.smarthouse.framework.domain.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:42 PM
 */
@Repository
public interface UserPlanRepository extends JpaRepository<Plan, Integer> {
    //通过电话查询该用户所有计划
    public List<Plan> findByPhone(String phone);
}
