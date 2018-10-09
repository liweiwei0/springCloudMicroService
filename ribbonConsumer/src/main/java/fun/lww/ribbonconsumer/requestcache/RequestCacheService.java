package fun.lww.ribbonconsumer.requestcache;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import fun.lww.ribbonconsumer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 请求缓存
 */
public class RequestCacheService {

    @Autowired
    private RestTemplate restTemplate;

    //添加缓存 @CacheResult() 缓存的key是所有的参数 当前为id
    //添加缓存 @CacheResult(cacheKeyMethod = "getCacheKey") 指定缓存key的生成方法
    //添加缓存 也可以使用@CacheKey("id")来指定缓存key 如果使用了cacheKeyMethod那么@CacheKey注解不会生效 此注解优先级较低
    //  如果方法参数是对象 也可以指定对象中的属性作为缓存的key 即 @CacheKey("对象属性")
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public User getUserById(/*@CacheKey("id")*/ Long id) {
        return restTemplate.getForObject("http://helloservice/users/{1}", User.class, id);
    }

    //删除缓存 在数据更新是清除缓存 commandKey参数必须 指定需要使用的请求缓存的请求命令
    @CacheRemove(commandKey = "getUserById")
    @HystrixCommand
    public void update(@CacheKey("id") User user) {
        restTemplate.postForObject("http://helloservice/users", user, User.class);
    }

    private Long getCacheKey(Long id) {
        return id;
    }

}
