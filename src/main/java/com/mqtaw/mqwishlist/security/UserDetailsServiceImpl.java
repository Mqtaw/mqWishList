//package com.mqtaw.mqwishlist.security;
//
//import com.mqtaw.mqwishlist.repository.UserRepository;
//import com.mqtaw.mqwishlist.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER;"));
//        String password = userService.findUserByUsername(username).getPassword();
//        UserDetails userDetails = User.withUsername(username)
//                .password(password)
//                .authorities(authorities)
//                .build();
//
//        return userDetails;
//    }
//}
