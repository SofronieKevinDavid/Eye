package com.kevin;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        User user=new User("David",17);
        UserRepository userRepository=new UserRepository();
        try {
            //userRepository.getConnection();
            //userRepository.insertValues("Andreiu",1222);
            //userRepository.demoDelete("opma",121);



            userRepository.updateAgeUserRepository(100,"kevin",13);
            userRepository.updateNameUserRepository("Claudel","Caludel",12);
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
