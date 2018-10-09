package fun.lww.ribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    @GetMapping("/ribbon-consumer")
    public String helloConsumer() {
        return restTemplate.getForEntity("http://helloservice/hello", String.class).getBody();
    }

    @GetMapping("/hello")
    public List hello() {
        return helloService.helloService();
    }

}
