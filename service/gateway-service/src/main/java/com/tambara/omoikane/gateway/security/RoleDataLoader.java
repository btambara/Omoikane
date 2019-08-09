package com.tambara.omoikane.gateway.security;

import com.tambara.omoikane.gateway.model.Privilege;
import com.tambara.omoikane.gateway.model.Role;
import com.tambara.omoikane.gateway.model.User;
import com.tambara.omoikane.gateway.repository.PrivilegeRepository;
import com.tambara.omoikane.gateway.repository.RoleRepository;
import com.tambara.omoikane.gateway.service.UserAuthenticationBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class RoleDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserAuthenticationBaseService userAuthenticationService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        final Privilege h2ConsolePrivilege = createPrivilegeIfNotFound("H2_CONSOLE_ACCESS");
        final Privilege eurekaPrivilege = createPrivilegeIfNotFound("EUREKA_ACCESS");

        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(h2ConsolePrivilege, eurekaPrivilege, readPrivilege, writePrivilege, passwordPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, passwordPrivilege));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        final Role userRole = createRoleIfNotFound("ROLE_USER", userPrivileges);

        // == create initial user
        createUserIfNotFound("admin", "A1vj%40hsU%k", new ArrayList<>(Collections.singletonList(adminRole)));
        createUserIfNotFound("user", "password", new ArrayList<>(Collections.singletonList(userRole)));
        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    private User createUserIfNotFound(final String username, final String password, final Collection<Role> roles) {
        User user = new User();
        user.setId(-1L);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRoles(roles);
        return userAuthenticationService.registerUser(user);
    }

}