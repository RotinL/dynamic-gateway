package top.luo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luo.constant.MqConstant;
import top.luo.model.GatewayRouteDefinition;
import top.luo.mq.PublishProducer;
import top.luo.service.RouteService;
import top.luo.util.RedisCache;

import java.util.List;

/**
 * @author luo
 * @date 2021-01-30
 */
@Slf4j
@RestController
@RequestMapping("route")
public class RouteController {

    private RouteService service;

    @Autowired
    public void setService(RouteService service) {
        this.service = service;
    }

    private RedisCache redisCache;

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @GetMapping
    public List<GatewayRouteDefinition> list() {
        return service.listAll();
    }

    private PublishProducer producer;

    @Autowired
    public void setProducer(PublishProducer producer) {
        this.producer = producer;
    }

    @GetMapping("publish")
    public String publish() {
        redisCache.deleteObject(MqConstant.REDIS_ROUTES_KEY);
        log.info("-- 删除原路由缓存 ------------------------------");
        List<GatewayRouteDefinition> routeDefinitions = service.listAll();
        redisCache.setCacheList(MqConstant.REDIS_ROUTES_KEY, routeDefinitions);
        log.info("-- 添加缓存: {} ------------------------------", routeDefinitions);
        // 发布更新路由消息
        Message message =
                new Message(MqConstant.TOPIC_PUBLISH, MqConstant.TAG_PUBLISH, MqConstant.PUBLISH.getBytes());
        try {
            SendResult sendResult = producer.getProducer().send(message);
            log.info("-- 发布消息: {}", sendResult);
        } catch (Exception e) {
            return "发布消息失败";
        }
        return "success";
    }

}
