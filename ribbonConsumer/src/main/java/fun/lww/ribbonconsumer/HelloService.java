package fun.lww.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey")
    public List helloService() {
        return restTemplate.getForEntity("http://helloservice/hello", List.class).getBody();
    }

    @HystrixCommand
    public User getUserById(Long id) {
        return restTemplate.getForObject("http://helloservice/users/{1}", User.class, id);
    }

    @HystrixCommand
    public Future<User> getUserByIdAsync(final Long id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://helloservice/users/{1}", User.class, id);
            }
        };
    }

    public List helloFallback(Throwable e) {
        System.out.println("error");
        List list = new ArrayList();
        list.add("error");
        return list;
    }

}
