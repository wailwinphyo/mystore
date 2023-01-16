package com.codex.mystore.Init;


import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.dao.repo.TeamMemberRepository;
import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.models.role.Role;
import com.codex.mystore.models.team.TeamMember;
import com.codex.mystore.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /*@Autowired
    private PrivilegeRepository privilegeRepository;*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //TeamMember teamMember = teamMemberRepository.findTeamMemberByUser(new User(1L));

        //System.out.println(teamMember);


        /*Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);*/

        createRoleIfNotFound("ROLE_ADMIN");//adminPrivileges
        createRoleIfNotFound("ROLE_USER");//Arrays.asList(readPrivilege)

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");

        if (!checkUserExists("test5@test.com")) {
            User user = new User();
            user.setName("Tester 55");
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("test5@test.com");
            user.setRoles(Arrays.asList(adminRole, userRole));
            user.setEnabled(true);
            userRepository.save(user);

            User user1 = new User();
            user1.setName("Tester 44");
            user1.setPassword(passwordEncoder.encode("test"));
            user1.setEmail("test4@test.com");
            user1.setRoles(Arrays.asList(userRole));
            user1.setEnabled(true);
            userRepository.save(user1);
        }
    }

    private boolean checkUserExists(String email) {
        User user = userRepository.findByEmail(email);
        return user == null ? false : true;
    }

    /*@Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }*/

    @Transactional
    Role createRoleIfNotFound(
            String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            //role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
