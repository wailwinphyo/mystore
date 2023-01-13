package com.codex.mystore.dao.impl;

import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.models.user.MyUserDetails;
import com.codex.mystore.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsIImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null) throw new UsernameNotFoundException("User not found : " + email);

        return MyUserDetails.build(user);
    }

    /*private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<String> privileges = roles.stream().map(x -> x.getName()).collect(Collectors.toList());
        return getGrantedAuthorities(privileges);

        //getPrivileges(roles)
    }*/

    /*private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            //collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }*/

    /*private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }*/
}
