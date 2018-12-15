package com.kevin.service;


import com.kevin.domain.GameDefinition;
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
    public void saveUser(UserDTO userDTO){
        if(userDTO.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }


        User userObject=convert(userDTO);

        //if(user.getId()==0){
        //throw new IllegalArgumentException("ID can not be 0.");
        //}
        try {
            userRepository.save(userObject);
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

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setID(user.getId());
        return userDTO;
    }

    private User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setId(userDTO.getID());
        return user;
    }

    public UserDTO getUserById(long id) {
        User user=userRepository.findOne(id);
        if(user==null){
            throw new IllegalArgumentException("The id is not valid.");
        }
        return convertToDto(user);
    }

    public UserDTO updateUser(long id,UserDTO dto) {
        User user=userRepository.findOne(id);
        user.setName(dto.getName());

        User savedObject= userRepository.save(user);

        return convertToDto(savedObject);
    }

    public boolean deleteUserById(long id){
        if(userRepository.findOne(id)!=null) {
            userRepository.delete(id);
            return true;
        }
        return false;
    }
}

