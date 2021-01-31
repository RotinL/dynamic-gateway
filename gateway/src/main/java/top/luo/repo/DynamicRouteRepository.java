package top.luo.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.luo.constant.MqConstant;
import top.luo.model.GatewayRouteDefinition;
import top.luo.util.RedisCache;
import top.luo.util.RouteDefinitionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luo
 * @date 2021-01-30 14:49
 */
@Slf4j
@Component
public class DynamicRouteRepository implements RouteDefinitionRepository {

    private RedisCache redisCache;

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        log.info("-- 重新加载路由开始 --------------------------");
        // 从 redis 获取路由集合
        List<GatewayRouteDefinition> routes = redisCache.getCacheList(MqConstant.REDIS_ROUTES_KEY);
        log.info("-- 路由集合: {} --------------------------", routes);
        // 转 RouteDefinition
        return Flux.fromIterable(
                routes.stream()
                        .map(RouteDefinitionUtils::assembleRouteDefinition).collect(Collectors.toList()));
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.empty();
    }

    /*
       // 路由结构
       RouteDefinition definition = new RouteDefinition();
        definition.setId("provider");
        definition.setUri(URI.create("lb://provider"));
        // 断言
        definition.setPredicates(Collections.singletonList(new PredicateDefinition() {{
            setName("Path");
            setArgs(new HashMap<String, String>() {{
                put("pattern", "/provider1/**");
            }});
        }}));
        // 过滤
        definition.setFilters(Collections.singletonList(new FilterDefinition() {{
            setName("RewritePath");
            setArgs(new HashMap<String, String>(2) {{
                put("regexp", "/provider1(?<segment>/?.*)");
                put("replacement", "/$\\{segment}");
            }});
        }}));

        */

}
