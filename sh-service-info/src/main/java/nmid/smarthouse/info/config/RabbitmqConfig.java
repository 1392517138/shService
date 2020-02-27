package nmid.smarthouse.info.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/16 8:45 PM
 */
@Configuration
public class RabbitmqConfig {
    //交换机名称
    public static final String EX_ROUTING_CAPTCHA = "ex_routing_captcha";

    @Bean(EX_ROUTING_CAPTCHA)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.directExchange(EX_ROUTING_CAPTCHA).durable(true).build();
    }
}
