package fun.lww.ribbonconsumer.requestcollapser;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import fun.lww.ribbonconsumer.User;

import java.util.List;

/**
 * 为请求合并的实现做一个批量请求命令实现
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    private UserService userService;
    private List<Long> userIds;

    public UserBatchCommand(UserService userService, List<Long> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommand")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() {
        return userService.findAll(userIds);
    }

}
