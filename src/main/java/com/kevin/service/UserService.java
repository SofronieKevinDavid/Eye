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
    public void saveUser(UserDTO userDTO){
        if(userDTO.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }

            User userObject = convert(userDTO);
            try {
                userRepository.save(userObject);
            } catch (Exception e) {
                System.out.println("Error in saving user " + e);
            }
    }

    public List<UserDTO> getUserByName(String name){
        List<User> list= userRepository.findByName(name);
        List<UserDTO> listDTO =new ArrayList<>();
        for(int i=0;i<list.size();i++){
         listDTO.add(convertToDto(list.get(i)));
        }
        return listDTO;
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
            userDTO.setPassword(user.getPassword());


            list.add(userDTO);
        }

        return list;
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setID(user.getId());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    private User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setId(userDTO.getID());
        user.setPassword(userDTO.getPassword());
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
        user.setPassword(dto.getPassword());
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

