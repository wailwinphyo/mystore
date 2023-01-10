//package com.codex.mystore.Config;
//
//import com.codex.mystore.Dao.UserRepository;
//import com.codex.mystore.Models.User.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        final User user = userRepository.findByEmail(authentication.getName());
//
//        final Authentication result = super.authenticate(authentication);
//
//        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
//    }
//}
