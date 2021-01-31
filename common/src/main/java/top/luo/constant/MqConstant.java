package top.luo.constant;

/**
 * @author luo
 * @date 2021-01-30
 */
public class MqConstant {

    /**
     * Name Server 地址，集群部署 可以有多个用 分号 隔开
     */
    public static final String NAME_SERVER = "192.168.31.100:9876";

    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String TOPIC_PUBLISH = "topic_publish";

    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP_PUBLISH = "consumer_group_publish";

    /**
     * 生产者组
     */
    public static final String PRODUCER_GROUP_PUBLISH = "producer_group_publish";

    public static final String PUBLISH = "publish";

    public static final String TAG_PUBLISH = "tag_publish";

    public static final String REDIS_ROUTES_KEY = "routes";

}
