package fun.lww.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import fun.lww.ribbonconsumer.User;
import org.springframework.web.client.RestTemplate;

public class UserGetCommand extends HystrixCommand<User> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");
    private RestTemplate restTemplate;
    //当前用户ID
    private Long id;

    protected UserGetCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() {
        return restTemplate.getForObject("http://helloservice/users/{1}", User.class, id);
    }

    /**
     * 开启请求缓存功能  减轻高并发情况下的线程消耗 降低请求响应时间
     * @return
     */
    @Override
    protected String getCacheKey() {
        //根据ID置入缓存
        return String.valueOf(id);
    }

    /**
     * 刷新缓存 根据ID进行清理
     * @param id
     */
    public static void flushCache(Long id) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
