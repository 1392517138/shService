package nmid.smarthouse.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/26 2:18 PM
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan("nmid.smarthouse.framework.domain.entity")//扫描实体类
@EnableJpaRepositories(basePackages = "nmid.smarthouse.data.dao")
@ComponentScan(basePackages = {"nmid.smarthouse.api"})//扫描接口
@ComponentScan(basePackages = {"nmid.smarthouse.framework.model"})//扫描common包下的类
@ComponentScan(basePackages = {"nmid.smarthouse.data"})//扫描本项目下的所有类
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}
