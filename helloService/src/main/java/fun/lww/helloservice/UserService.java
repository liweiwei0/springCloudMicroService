package fun.lww.helloservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L, "zs"));
        users.add(new User(2L, "ls"));
        users.add(new User(3L, "ww"));
        users.add(new User(4L, "ll"));
        users.add(new User(5L, "li"));
    }

    public User find(Long id) {
        System.out.println(id);
        return users.get(new Random().nextInt(5));
    }

    public List<User> findAll(String ids) {
        System.out.println(ids);
        return users;
    }
}
