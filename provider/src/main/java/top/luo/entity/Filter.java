package top.luo.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author luo
 * @date 2021-01-30
 */
@Data
@Table(name = "gateway_filter_definition")
public class Filter implements Serializable {

    private static final long serialVersionUID = 1884318910789936530L;

    @Id
    private Long id;

    private Long routeId;

    private String name;

    private String args;

}
