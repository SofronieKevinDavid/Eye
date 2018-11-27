package com.kevin.service;


import com.kevin.domain.User;
import com.kevin.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        if(user.getName()==null){
            throw new IllegalArgumentException("Name can not be null.");
        }

        if(user.getAge()<=0){
            throw new IllegalArgumentException("Age must be greater than 0.");
        }

        //if(user.getID()==0){
            //throw new IllegalArgumentException("ID can not be 0.");
        //}
        try {
            userRepository.save(user);
        }catch (Exception e){
            System.out.println("Error in saving user "+e);
        }
    }
}
