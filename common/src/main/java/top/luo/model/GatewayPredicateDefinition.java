package top.luo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言模型
 *
 * @author luo
 * @date 2021-01-30 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatewayPredicateDefinition implements Serializable {

    private static final long serialVersionUID = -6704265135181201793L;

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
