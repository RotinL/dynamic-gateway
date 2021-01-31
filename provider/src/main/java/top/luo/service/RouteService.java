package top.luo.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.luo.entity.Filter;
import top.luo.entity.Predicate;
import top.luo.entity.Route;
import top.luo.mapper.FilterMapper;
import top.luo.mapper.PredicateMapper;
import top.luo.mapper.RouteMapper;
import top.luo.model.GatewayFilterDefinition;
import top.luo.model.GatewayPredicateDefinition;
import top.luo.model.GatewayRouteDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 路由 Service
 *
 * @author luo
 * @date 2021-01-30
 */
@Service
public class RouteService {

    private RouteMapper routeMapper;

    @Autowired
    public void setRouteMapper(RouteMapper routeMapper) {
        this.routeMapper = routeMapper;
    }

    private PredicateMapper predicateMapper;

    @Autowired
    public void setPredicateMapper(PredicateMapper predicateMapper) {
        this.predicateMapper = predicateMapper;
    }

    private FilterMapper filterMapper;

    @Autowired
    public void setFilterMapper(FilterMapper filterMapper) {
        this.filterMapper = filterMapper;
    }

    @SuppressWarnings("all")
    public List<GatewayRouteDefinition> listAll() {
        List<GatewayRouteDefinition> resList = new ArrayList<>();
        // 查询全部路由
        routeMapper.select(new Route()).forEach(route -> {
            GatewayRouteDefinition routeDefinition = GatewayRouteDefinition.builder()
                    .id(route.getDefinitionId()).uri(route.getUri()).order(route.getRouteOrder()).build();
            // 断言处理
            Example example = new Example(Predicate.class);
            example.createCriteria().andEqualTo("routeId", route.getId());
            List<GatewayPredicateDefinition> predicateDefinitions =
                    predicateMapper.selectByExample(example).stream().map(predicate -> {
                        return GatewayPredicateDefinition.builder()
                                .name(predicate.getName()).args(JSON.parseObject(predicate.getArgs(), Map.class)).build();
                    }).collect(Collectors.toList());

            // 过滤处理
            example = new Example(Filter.class);
            example.createCriteria().andEqualTo("routeId", route.getId());
            List<GatewayFilterDefinition> filterDefinitions =
                    filterMapper.selectByExample(example).stream().map(filter -> {
                        return GatewayFilterDefinition.builder()
                                .name(filter.getName()).args(JSON.parseObject(filter.getArgs(), Map.class)).build();
                    }).collect(Collectors.toList());
            // 添加到返回结果
            resList.add(routeDefinition.withPredicates(predicateDefinitions).withFilters(filterDefinitions));
        });
        return resList;
    }

}
