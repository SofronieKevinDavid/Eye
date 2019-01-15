package com.kevin.persistance;


import com.kevin.domain.User;
import com.kevin.dto.UserDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface UserRepository extends
        PagingAndSortingRepository<User, Long>{

    User findByName(String name);
}
