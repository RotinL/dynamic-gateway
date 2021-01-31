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
@Table(name = "gateway_predicate_definition")
public class Predicate implements Serializable {

    private static final long serialVersionUID = -2695945813366810974L;

    @Id
    private Long id;

    private Long routeId;

    private String name;

    private String args;

}
