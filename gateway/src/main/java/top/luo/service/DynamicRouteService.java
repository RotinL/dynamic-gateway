package top.luo.service;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

/**
 * @author luo
 * @date 2021-01-30 14:58
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {


    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(@Nonnull ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }

    public void refresh() {
        // 发布事件，重新加载路由
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
