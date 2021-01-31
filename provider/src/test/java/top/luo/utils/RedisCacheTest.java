package top.luo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.luo.util.RedisCache;

/**
 * @author luo
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisCacheTest {

    private RedisCache redisCache;

    @Autowired
    public void setRedisCacheTemplate(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Test
    public void test1() throws Exception {
        redisCache.setCacheObject("key", "value");
    }

}
