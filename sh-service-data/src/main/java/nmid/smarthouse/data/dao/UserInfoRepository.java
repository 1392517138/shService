package nmid.smarthouse.data.dao;

import nmid.smarthouse.framework.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/19 1:42 PM
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
