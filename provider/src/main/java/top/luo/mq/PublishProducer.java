package top.luo.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;
import top.luo.constant.MqConstant;

/**
 * @author luo
 * @date 2021-01-30 16:34
 */
@Slf4j
@Component
public class PublishProducer {

    private final DefaultMQProducer producer;

    public PublishProducer() {
        // 生产者
        producer = new DefaultMQProducer(MqConstant.PRODUCER_GROUP_PUBLISH);
        // 不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        // 绑定name server
        producer.setNamesrvAddr(MqConstant.NAME_SERVER);
        start();
    }

    /**
     * 对象在使用之前必须要调用一次，只能初始化一次
     */
    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("-- Producer 启动成功 ----------------------");
    }

    public DefaultMQProducer getProducer() {
        return this.producer;
    }

    /**
     * 一般在应用上下文，使用上下文监听器，进行关闭
     */
    public void shutdown() {
        this.producer.shutdown();
    }

}
