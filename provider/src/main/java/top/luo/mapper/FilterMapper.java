package top.luo.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import top.luo.entity.Filter;

/**
 * 过滤 mapper
 *
 * @author 75020
 * @since 2020/01/30
 */
@Repository
public interface FilterMapper extends Mapper<Filter> {
}
