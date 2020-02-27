package nmid.smarthouse.framework.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 2:33 PM
 */
@Data
@AllArgsConstructor
@Entity(name = "user_info")
public class UserInfo {
    @Id
    private String phone;

    private Double temperature;

    private Double moisture;

    private String height;
    private String weight;

    @Column(name = "b_Temperature")
    private String btemperature;
    @Column(name = "b_Sugar")
    private String bsugar;
    @Column(name = "b_Oxygen")
    private String boxygen;
    @Column(name = "h_Rate")
    private String hrate;
    @Column(name = "n_Measure")
    private String nmeasure;

}
