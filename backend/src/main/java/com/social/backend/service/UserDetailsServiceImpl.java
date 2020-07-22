package com.social.backend.service;

import com.social.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.findByUsername(username);
        if(null == user){
            System.out.println("User not found " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("Found User: " + username);
//        Set<Role> roleSet = user.getRoles();
        List<String> roleNames = new ArrayList<>();
        if( user.getUsername().equals("admin") ){
            roleNames.add("ADMIN");
        }
        if( user.getUsername().equals("user") ){
            roleNames.add("USER");
        }
//        for(Role role : roleSet){
//            roleNames.add(role.getName());
//        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (null != roleNames){
            for (String rol : roleNames){
                GrantedAuthority authority = new SimpleGrantedAuthority(rol);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
