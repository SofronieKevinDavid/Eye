package com.kevin.web;

import com.kevin.dto.UserDTO;
import com.kevin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable("id") long id){
        return userService.getUserById(id);
    }


    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public UserDTO getUser(@RequestParam("name") String name){
        return userService.getUserByName(name);
    }

    @RequestMapping(path="/user", method=RequestMethod.POST)
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return userDTO;
    }

    @RequestMapping(path="/user/{id}", method=RequestMethod.PUT)
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO dto){
        return userService.updateUser(id, dto);
    }

    @RequestMapping(path = "/user/{id}", method =RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
    }
}
