package nmid.smarthouse.info_client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

    //队列bean的名称
    public static final String QUEUE_REGIST_SMS = "queue_regist_sms";
    //交换机的名称
    public static final String EX_ROUTING_CAPTCHA = "ex_routing_captcha";
    //队列的名称
    @Value("${smarthouse.mq.queue}")
    public String queue_cms_postpage_name;
    //routingKey 即站点Id
    @Value("${smarthouse.mq.routingKey}")
    public String routingKey;

    /**
     * 交换机配置使用direct类型
     *
     * @return the exchange
     */
    @Bean(EX_ROUTING_CAPTCHA)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.directExchange(EX_ROUTING_CAPTCHA).durable(true).build();
    }

    //声明队列
    @Bean(QUEUE_REGIST_SMS)
    public Queue QUEUE_CMS_POSTPAGE() {
        Queue queue = new Queue(queue_cms_postpage_name);
        return queue;
    }

    /**
     * 绑定队列到交换机
     *
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding BINDING_QUEUE_INFORM_SMS(@Qualifier(QUEUE_REGIST_SMS) Queue queue, @Qualifier(EX_ROUTING_CAPTCHA) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }

}
