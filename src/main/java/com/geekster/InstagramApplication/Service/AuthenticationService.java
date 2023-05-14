package com.geekster.InstagramApplication.Service;

import com.geekster.InstagramApplication.Model.AuthenticationToken;
import com.geekster.InstagramApplication.Model.User;
import com.geekster.InstagramApplication.Repositary.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepo tokenRepo;


    public void saveToken(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public boolean authenticate(String userEmail, String token) {
        AuthenticationToken authToken = tokenRepo.findFirstByToken(token);
        String expectedEmail = authToken.getUser().getUserEmail();
        return expectedEmail.equals(userEmail);
    }
}
