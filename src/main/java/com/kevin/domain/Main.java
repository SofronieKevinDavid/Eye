/*
package com.kevin.domain;

import com.kevin.persistance.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user=new User("David",17);
        UserRepository userRepository=new UserRepository();
        try {
            //userRepository.getConnection();
            userRepository.createUser(user);
            //userRepository.deleteUser("David",100);




            userRepository.updateAgeUser(100,"David",17);
            //userRepository.updateAgeUser("Caludel",12);
            userRepository.deleteUser("David",100);
            List l =userRepository.findAllUserRepository();
            for (int i = 0; i < l.size(); i++) {

                User u = (User)l.get(i);

                System.out.println(u.getName());
                System.out.println(u.getAge());
                System.out.println("--------------");

            }


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
*/
