package fun.lww.helloservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/find/{id}")
    public User find(@PathVariable("id") Long id) {
        return userService.find(id);
    }

    @GetMapping("/findAll")
    public List<User> findAll(String ids) {
        return userService.findAll(ids);
    }
}
