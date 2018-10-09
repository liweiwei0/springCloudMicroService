package fun.lww.helloservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello")
    public List<String> index() throws InterruptedException {
        List<String> services = client.getServices();
        services.add("port : "+port);

        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);

        return services;
    }
}
