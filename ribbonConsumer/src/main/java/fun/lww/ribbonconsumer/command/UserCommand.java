package fun.lww.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import fun.lww.ribbonconsumer.User;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    protected UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() {
        return restTemplate.getForObject("http://helloservice/users/{1}", User.class, id);
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
