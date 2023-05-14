package com.geekster.InstagramApplication.Controller;

import com.geekster.InstagramApplication.Dto.SignInInput;
import com.geekster.InstagramApplication.Dto.SignInOutput;
import com.geekster.InstagramApplication.Dto.SignUpInput;
import com.geekster.InstagramApplication.Dto.SignUpOutput;
import com.geekster.InstagramApplication.Model.User;
import com.geekster.InstagramApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public SignUpOutput signUp(SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }

    @PostMapping("/signIn")
    public SignInOutput signIn(SignInInput signInInput){
        return userService.signIn(signInInput);
    }

    @PutMapping("/update")
    public void Update(@RequestBody User user ,@RequestParam String token){
         userService.Update(user, token);
    }
}
