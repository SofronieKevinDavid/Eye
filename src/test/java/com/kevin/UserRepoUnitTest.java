package com.kevin;

import com.kevin.domain.User;
import com.kevin.dto.UserDTO;
import com.kevin.persistance.UserRepository;
import com.kevin.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserRepoUnitTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void testGetById(){
        User userToBeReturned=new User();
        userToBeReturned.setId(5L);
        userToBeReturned.setName("a");
        userToBeReturned.setPassword("a");

        Mockito.doReturn(userToBeReturned).when(repository).findOne(5L);

        UserDTO userDTO=service.getUserById(5L);

        Assert.assertNotNull(userDTO);
        Assert.assertEquals("a",userDTO.getName());
        Assert.assertEquals(5L, userDTO.getID());
    }
}
