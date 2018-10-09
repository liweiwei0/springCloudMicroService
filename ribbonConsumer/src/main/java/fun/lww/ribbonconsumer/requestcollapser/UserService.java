package fun.lww.ribbonconsumer.requestcollapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import fun.lww.ribbonconsumer.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 请求合并
 */
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    //方案一 继承方式
    public User find(Long id) {
        System.out.println(id);
        return restTemplate.getForObject("http://helloservice/user/find/{1}", User.class, id);
    }
    public List findAll(List<Long> ids) {
        System.out.println(ids);
        return restTemplate.getForObject("http://helloservice/user/findAll?ids={1}", List.class, StringUtils.join(ids
                , ","));
    }

    /* ========================================================================= */

    //方案二 注解方式
    /**
     * 使用注解实现请求合并器
     * 合并时间窗时间100ms
     */
    @HystrixCollapser(batchMethod = "findAll1", collapserProperties = {@HystrixProperty(name =
            "timerDelayInMilliseconds", value = "100")})
    public User find1(Long id) {
        System.out.println(id);
        return null;
    }
    @HystrixCommand
    public List findAll1(List<Long> ids) {
        System.out.println(ids);
        return restTemplate.getForObject("http://helloservice/user/findAll?ids={1}", List.class, StringUtils.join(ids
                , ","));
    }
}
