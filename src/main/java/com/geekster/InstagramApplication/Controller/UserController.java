package com.geekster.InstagramApplication.Controller;

import com.geekster.InstagramApplication.Dto.SignInInput;
import com.geekster.InstagramApplication.Dto.SignInOutput;
import com.geekster.InstagramApplication.Dto.SignUpInput;
import com.geekster.InstagramApplication.Dto.SignUpOutput;
import com.geekster.InstagramApplication.Model.User;
import com.geekster.InstagramApplication.Service.AuthenticationService;
import com.geekster.InstagramApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public SignUpOutput signUp(SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }

    @PostMapping("/signIn")
    public SignInOutput signIn(SignInInput signInInput){
        return userService.signIn(signInInput);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam String email, @RequestParam String token, @RequestBody User user) {
        HttpStatus status;
        String message = null;
        if(authenticationService.authenticate(email,token))
        {
            try {
                userService.updateUser(user, token);
                status = HttpStatus.OK;
            } catch (Exception e) {
                message = "invalid information";
                status = HttpStatus.BAD_REQUEST;

            }

        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }



        return new ResponseEntity<String>(message,status);
    }

    @PostMapping("follow/{myId}/{otherId}")
    public String followUser(@PathVariable Long myId, @PathVariable Long otherId){
        return userService.followUser(myId, otherId);
    }
}
