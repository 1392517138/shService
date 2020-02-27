package nmid.smarthouse.info_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/20 11:07 AM
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"nmid.smarthouse.framework.model"})//扫描common包下的类
@ComponentScan(basePackages = {"nmid.smarthouse.info_client"})//扫描本项目下的所有类
public class InfoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfoClientApplication.class, args);
    }
}
