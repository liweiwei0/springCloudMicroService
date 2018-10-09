# springcloud
> SpringCloud微服务实战

## actuator 监控和管理
> /actuator 端点 包含所有监控端点信息

### 应用配置
> 在启动时就已经确定
*  /autoconfig  该端点用来获取应用的自动配置报告 
* /actuator/beans  该端点用来获取应用上下文中创建的所有bean
* /actuator/configprops  该端点用来获取应用中配置的属性信息报告
* /actuator/env  该端点用来获取应用所有可用的环境属性报告  可以从中获取配置信息在项目中使用
* /actuator/env/{toMatch}
* /actuator/mappings  该端点用来返回所有的spring mvc的控制器映射关系报告
* /actuator/info  该端点定义一些应用自定义的信息
* /actuator/auditevents
* /actuator/conditions
* /actuator/scheduledtasks

### 度量指标
> 提供应用程序在运行过程中的一些快照信息
* /actuator/metrics  该端点用来返回当前应用的各类重要度量指标 例如内存信息 线程信息 垃圾回收信息  此端点返回指标名称 明细由子端点实现
* /actuator/metrics/{requiredMetricName}
* /actuator/health  获取应用各类健康指标信息
* /actuator/heapdump 用来暴露程序运行中堆信息 
* /actuator/threaddump  用来暴露程序运行中线程信息
* /actuator/httptrace  该端点返回基本的HTTP跟踪信息
* /actuator/loggers/{name}
* /actuator/loggers

### 操作控制
* /actuator/shutdown  通过访问端点实现远程关闭应用操作

## eureka 服务治理

### Eureka客户端配置详解
* 服务注册
* 服务实例

## ribbon 负载均衡

## hystrix 断路器
> 断路器 服务降级 请求缓存 请求合并