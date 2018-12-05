package org.kevin.eye;

import com.kevin.DTO.UserDTO;
import com.kevin.domain.User;
import com.kevin.service.UserService;
import com.kevin.EyeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EyeApplication.class, loader = SpringApplicationContextLoader.class, initializers = ConfigFileApplicationContextInitializer.class)
public class UserIntegrationTest {
    @Autowired
    private UserService userService;



    @Test
    public void testFind() {

        /*User user=new User("codyy17",1111);
        user.setName("codyy17");
        user.setAge(11112);
        userService.saveUser(user);*/

        List<UserDTO> list=userService.getUsers();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}

