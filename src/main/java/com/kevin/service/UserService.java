package com.kevin.service;


import com.kevin.dto.UserDTO;
import com.kevin.domain.User;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //@Transactional
    public void saveUser(User user){
        if(user.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }

        if(user.getAge()<=0){
            throw new IllegalArgumentException("Age must be greater than 0.");
        }

        //if(user.getId()==0){
        //throw new IllegalArgumentException("ID can not be 0.");
        //}
        try {
            userRepository.save(user);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }

    @Transactional
    public List<UserDTO> getUsers() {
        Iterator<User> iterator =
                userRepository.findAll().iterator();


        List<UserDTO> list = new ArrayList<>();

        while (iterator.hasNext()) {
            User user = iterator.next();

            UserDTO userDTO = new UserDTO("UserDTO");
            userDTO.setName(user.getName());
            userDTO.setID(user.getId());


            list.add(userDTO);
        }

        return list;
    }
}

