package org.kevin.eye;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EyeApplication.class, loader = SpringApplicationContextLoader.class, initializers = ConfigFileApplicationContextInitializer.class)
public class UserIntegrationTest {
    @Autowired
    private UserService userService;



    @Test
    public void testFind() {

        User user=new User("codyy",11);
        user.setName("codyy");
        user.setAge(11);
        userService.saveUser(user);

    }
}

