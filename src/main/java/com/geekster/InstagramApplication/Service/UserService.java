package com.geekster.InstagramApplication.Service;

import com.geekster.InstagramApplication.Dto.SignInInput;
import com.geekster.InstagramApplication.Dto.SignInOutput;
import com.geekster.InstagramApplication.Dto.SignUpInput;
import com.geekster.InstagramApplication.Dto.SignUpOutput;
import com.geekster.InstagramApplication.Model.AuthenticationToken;
import com.geekster.InstagramApplication.Model.User;
import com.geekster.InstagramApplication.Repositary.TokenRepo;
import com.geekster.InstagramApplication.Repositary.UserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    TokenRepo tokenRepo;

    public SignUpOutput signUp(SignUpInput signUpInput) {

        //Check already exist by email

        User user = userRepo.findFirstByUserEmail(signUpInput.getUserEmail());
        if(user != null){
            throw new IllegalStateException("User already Exist...!!!");
        }

        //encryption
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //save the User
        user = new User(signUpInput.getUserAge(), signUpInput.getUserFirstName(), signUpInput.getUserLastName(), signUpInput.getUserEmail()
                           , signUpInput.getUserPassword(), signUpInput.getUserPhoneNumber());

        userRepo.save(user);

        //token Creation and saving
        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveToken(token);

        return new SignUpOutput("User Registered", "User Created Successfully...!!!");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInInput) {
        //get email
        User user = userRepo.findFirstByUserEmail(signInInput.getUserEmail());
        if(user == null){
            throw new IllegalStateException("User Invalid...!!!");
        }

        //encrypt password
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signInInput.getUserPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //match it with data base encrypted pass
        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());

        if(!isPasswordValid){
            throw new IllegalStateException("User Invalid...!!!");
        }

        //figure out token
        AuthenticationToken authToken = authenticationService.getToken(user);

        //set up output response
        return new SignInOutput("Authentication Successfull...!!1", authToken.getToken());

    }

    public void Update(User user, String token) {
        User user1 = tokenRepo.findFirstByToken(token).getUser();;
        user1.setUserAge(user.getUserAge());
        user1.setUserPassword(user.getUserPassword());
        user1.setUserEmail(user.getUserEmail());
        user1.setUserPhoneNumber(user.getUserPhoneNumber());
        user1.setUserFirstName(user.getUserFirstName());
        user1.setUserLastName(user.getUserLastName());

    }

    public void updateUser(User user, String token) {
        User user1 = tokenRepo.findFirstByToken(token).getUser();
        if ( user.getUserFirstName() != null) {
            user1.setUserFirstName(user.getUserFirstName());
        }
        if ( user.getUserLastName() != null) {
            user1.setUserLastName(user.getUserLastName());
        }
        if ( user.getUserEmail() != null) {
            Pattern p = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");

            Matcher m = p.matcher(user.getUserEmail());
            if( (m.find() && m.group().equals(user.getUserEmail()))){
                user1.setUserEmail(user.getUserEmail());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
//            user1.setEmail(user.getEmail());
        }
        if ( user.getUserAge() != null ) {
            user1.setUserAge(user.getUserAge());
        }
        if( user.getUserPhoneNumber() != null){
            Pattern p = Pattern.compile("\\d{2}-\\d{10}");

            Matcher m = p.matcher(user.getUserPhoneNumber());
            if( (m.find() && m.group().equals(user.getUserPhoneNumber()))){
                user1.setUserPhoneNumber(user.getUserPhoneNumber());

            }else{
                throw new IllegalStateException("Enter correct details");
            }

        }

        if ( user.getUserPassword() != null) {
            String encryptedPassword = null;
            try {
                encryptedPassword = encryptPassword(user.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            user1.setUserPassword(encryptedPassword);
        }
        userRepo.save(user1);
    }

    @Transactional
    public String followUser(Long myId, Long otherId) {
        if(myId==otherId){
            return "Cant follow Yourself...!!"
        }
        else {

        }
    }
}
