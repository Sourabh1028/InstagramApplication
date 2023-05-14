package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findFirstByUserEmail(String userEmail);

}
