package com.numeratorx.usersmanagement.security;

import com.numeratorx.usersmanagement.models.User;
import com.numeratorx.usersmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        User user = this.findUser(param);
        if( user != null){
            return new ApplicationUserDetails(user);
        } else {
            throw new UsernameNotFoundException(param + " not found");
        }
    }

    private User findUser(String param) {
        User user;
        user = userRepository.findByUsername(param);
        if (user != null && user.getId() != null) return user;

        user = userRepository.findByEmailId(param);
        if (user != null && user.getId() != null) return user;

        user = userRepository.findByMobileNumber(Long.parseLong(param));
        if (user != null && user.getId() != null) return user;
        return user;
    }
}
