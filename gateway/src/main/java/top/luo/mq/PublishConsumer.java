package top.luo.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.luo.constant.MqConstant;
import top.luo.service.DynamicRouteService;

import java.nio.charset.StandardCharsets;

/**
 * @author luo
 * @date 2021-01-30 16:35
 */
@Slf4j
@Component
public class PublishConsumer {

    private DynamicRouteService dynamicRouteService;

    @Autowired
    public void setDynamicRouteService(DynamicRouteService dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    /**
     * 通过构造函数 实例化对象
     */
    public PublishConsumer() throws MQClientException {
        // 消费者实体对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqConstant.CONSUMER_GROUP_PUBLISH);
        consumer.setNamesrvAddr(MqConstant.NAME_SERVER);
        // 消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(MqConstant.TOPIC_PUBLISH, "*");
        // 注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            for (Message msg : msgs) {
                // 消费者获取消息 这里只输出 不做后面逻辑处理
                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                log.info("-- PublishConsumer 获取消息 - topic: {} - msg: {}", msg.getTopic(), body);
                if (MqConstant.PUBLISH.equals(body)) {
                    // 刷新路由
                    dynamicRouteService.refresh();
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        log.info("-- PublishConsumer 启动成功 ---------------------------");
    }

}
