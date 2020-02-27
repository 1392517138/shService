package nmid.smarthouse.info.dao;

import nmid.smarthouse.framework.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/17 3:06 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByPhoneAndPwd(String phone, String pwd);

    public User findByPhone(String phone);

    @Query(value = "select family from User where phone=?1")
    public String findFamilyByPhone(String phone);

    @Query(value = "select count(*) from User where family like %:phone1% and phone= :phone2", nativeQuery = true)
    public int isFamily(@Param("phone1") String phone1, @Param("phone2") String pone2);

    /**
     * 用户修改名称、生日、性别、照片、密码、电话、添加/删除家人
     */
    @Modifying
    @Transactional
    @Query(value = "update User  set nickname=:name where phone=:phone")
    public int updateNickname(@Param("name") String name, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query(value = "update User  set borth=:borth where phone=:phone")
    public int updateBorth(@Param("borth") String borth, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query(value = "update User  set sex=:sex where phone=:phone")
    public int updateSex(@Param("sex") String sex, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query(value = "update User  set photo=:photo where phone=:phone")
    public int updatePhoto(@Param("photo") String photo, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query(value = "update User  set pwd=:pwd where phone=:phone")
    public int updatePwd(@Param("pwd") String pwd, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query(value = "update User  set phone=:phone1 where phone=:phone2")
    public int updatePhone(@Param("phone1") String phone1, @Param("phone1") String phone2);

    @Modifying
    @Transactional
    @Query(value = "update User  set family=:family where phone=:phone")
    public int addFamily(@Param("family") String family, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query(value = "update User set family = replace(family,:phone1,'') where phone=:phone2", nativeQuery = true)
    public int delFamily(@Param("phone1") String phone1, @Param("phone2") String phone2);
}
