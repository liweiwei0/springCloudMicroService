package fun.lww.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义 扩展一个健康指标检测器
 *
 * 1.实现HealthIndicator接口
 * 2.类名要求 xxxHealthIndicator xxx将会是你自定义得健康指标名称
 * 3.@Component注入到容器内
 * 4.重写health()方法
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        double b = check();
        System.out.println(b);
        if (b == 0) {
            return Health.unknown().withDetail("unknown", "不知道").build();
        }
        if (b == 1) {
            return Health.up().withDetail("up", "良好").build();
        }
        if (b > 0 && b < .5) {
            return Health.outOfService().withDetail("out_of_service", "暂停服务").build();
        }
        return Health.down().withDetail("down", "不太好").build();
    }

    // 检测是否健康的自定义逻辑
    private double check() {
        return Math.random();
    }
}
