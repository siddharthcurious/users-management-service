package com.numeratorx.usersmanagement.services;

import com.numeratorx.usersmanagement.configs.Roles;
import com.numeratorx.usersmanagement.models.User;
import com.numeratorx.usersmanagement.models.requests.LoginRequest;
import com.numeratorx.usersmanagement.models.requests.RegistrationRequest;
import com.numeratorx.usersmanagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User userExists(RegistrationRequest request){
        if(request.getUsername().isPresent()) {
            User foundUser = userRepository.findByUsername(request.getUsername().get());
            if(foundUser != null) return foundUser;
        }
        if(request.getEmailId().isPresent()) {
            User foundUser = userRepository.findByEmailId(request.getEmailId().get());
            if(foundUser != null) return foundUser;
        }
        if(request.getMobileNumber().isPresent()) {
            User foundUser = userRepository.findByMobileNumber(request.getMobileNumber().get());
            if(foundUser != null) return foundUser;
        }
        return null;
    }

    public User createUser(RegistrationRequest request){
        User user = new User();
        if(request.getUsername().isPresent())
            user.setUsername(request.getUsername().get());
        if(request.getEmailId().isPresent())
            user.setEmailId(request.getEmailId().get());
        if(request.getMobileNumber().isPresent())
            user.setMobileNumber(request.getMobileNumber().get());
        user.setGender(request.getGender());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Roles.DEFAULT_ROLE);

        user.setActive(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while registering user");
            return null;
        }
    }
}
