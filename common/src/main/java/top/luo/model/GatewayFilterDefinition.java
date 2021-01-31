package top.luo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器模型
 *
 * @author luo
 * @date 2021-01-30 14:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatewayFilterDefinition implements Serializable {

    private static final long serialVersionUID = -4349133193279241485L;

    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
