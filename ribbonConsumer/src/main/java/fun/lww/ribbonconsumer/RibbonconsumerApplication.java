package fun.lww.ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker //此注解用来开启断路器
@EnableDiscoveryClient
@SpringBootApplication
//这三个注解可以替换为 @SpringCloudApplication
//@SpringCloudApplication
public class RibbonconsumerApplication {

    @Bean
    @LoadBalanced //开启负载均衡
    RestTemplate init() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonconsumerApplication.class, args);
    }
}
