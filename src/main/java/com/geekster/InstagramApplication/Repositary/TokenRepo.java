package com.geekster.InstagramApplication.Repositary;

import com.geekster.InstagramApplication.Model.AuthenticationToken;
import com.geekster.InstagramApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);

}
