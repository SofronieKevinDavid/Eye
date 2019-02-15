package com.kevin.persistance;


import com.kevin.domain.User;
import com.kevin.dto.UserDTO;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends
        PagingAndSortingRepository<User, Long>{

    User findByName(String name);
}
////////////////////////////////////////////////////////////////////////////