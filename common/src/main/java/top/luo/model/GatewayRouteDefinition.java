package top.luo.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 路由模型
 *
 * @author luo
 * @date 2021-01-30 14:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class GatewayRouteDefinition implements Serializable {

    private static final long serialVersionUID = 7962985076524499120L;

    /**
     * 路由的Id
     */
    private String id;

    /**
     * 路由断言集合配置
     */
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    /**
     * 路由过滤器集合配置
     */
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private int order;

}
