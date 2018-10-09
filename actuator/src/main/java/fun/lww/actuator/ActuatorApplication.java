package fun.lww.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ActuatorApplication {

    @Value("${java.runtime.name}")
    String jvmProperties;

    @GetMapping("/jvm")
    @ResponseBody
    public String getJvmProperties() {
        System.out.println(jvmProperties);
        return jvmProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }
}
