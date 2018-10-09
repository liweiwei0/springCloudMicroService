package fun.lww.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import fun.lww.ribbonconsumer.User;
import org.springframework.web.client.RestTemplate;

public class UserPostCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private User user;

    protected UserPostCommand(RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")));
        this.restTemplate = restTemplate;
        this.user = user;
    }

    @Override
    protected User run() {
        //写操作
        User u = restTemplate.postForObject("http://helloservice/users", user, User.class);
        //刷新缓存 清理缓存中失效的User
        UserGetCommand.flushCache(u.getId());
        return u;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
