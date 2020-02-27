package nmid.smarthouse.framework.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * @author piwenjing
 * @description
 * @date 2020/2/15 9:26 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plan {

    @Id
    Integer id;
    String phone;
    String time;
    String thing;
}
