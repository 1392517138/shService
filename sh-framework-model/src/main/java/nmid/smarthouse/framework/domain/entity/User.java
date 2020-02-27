package nmid.smarthouse.framework.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * @author piwenjing
 * @description 用户基本信息
 * @date 2020/1/10 2:10 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String phone;
    private String pwd;
    private String nickname;
    private String borth;
    private String sex;
    private String photo;
    private String family;

}
