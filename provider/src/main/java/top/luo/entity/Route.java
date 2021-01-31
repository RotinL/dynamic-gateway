package top.luo.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author luo
 * @date 2021-01-30 17:33
 */
@Data
@Table(name = "gateway_route_definition")
public class Route implements Serializable {

    private static final long serialVersionUID = -2053557294822669050L;

    @Id
    private Long id;

    private String definitionId;

    private String uri;

    private Integer routeOrder;

    private String routeDesc;

}
