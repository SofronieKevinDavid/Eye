package com.kevin.service;


import com.kevin.dto.UserDTO;
import com.kevin.domain.User;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDTO userDTO){
        String username=userDTO.getName();
        if(username==null){
            throw new IllegalArgumentException("Name can not be null.");
        }
        boolean a=false;
        try{
            getUserByName(username);
        }catch(Exception e){
            User userObject = convert(userDTO);
            try {
                userRepository.save(userObject);
            } catch (Exception e2) {
                System.out.println("Error in saving user " + e2);
            }
            a=true;
        }
        if(a==false){
            throw new IllegalArgumentException("Name already taken.");
        }
    }

    public UserDTO getUserByName(String name){
        User user= userRepository.findByName(name);
        return convertToDto(user);
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

