package com.kevin;


import com.kevin.domain.User;
import com.kevin.persistance.UserRepository;
import com.kevin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EyeApplication.class,
        loader = SpringApplicationContextLoader.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class UserRepoIntegrationTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Test
    public void testSave(){
        User user=getUser("andrei44","parola");
        repository.save(user);

        User user2=getUser("andrei44","parola2");
        repository.save(user2);
    }

    @Test
    public void testGetByName(){
        System.out.print(service.getUserByName("alex"));
    }

    @Test
    public void deleteUserByName(){
        service.deleteUserById(8);
    }


    private User getUser(String name, String password){
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        return user;
    }
}
