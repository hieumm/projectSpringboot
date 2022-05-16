package com.example.projectb2c.security.auth;

import com.example.projectb2c.entity.UserEntity;
import com.example.projectb2c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;


public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepository.findByUsername(username);
        HttpSession httpSession = null;
        httpSession.setAttribute("userName",username);

        if(user==null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetail(user);
    }
}
