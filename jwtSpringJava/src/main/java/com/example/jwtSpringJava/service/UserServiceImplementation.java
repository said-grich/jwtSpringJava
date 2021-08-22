package com.example.jwtSpringJava.service;

import com.example.jwtSpringJava.entity.Role;
import com.example.jwtSpringJava.entity.User;
import com.example.jwtSpringJava.repository.RoleRepository;
import com.example.jwtSpringJava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username);
        if(user ==null){
            log.error("User not found in the data base");
            throw new UsernameNotFoundException("User not found");
        }else {
            log.info("User found in the dataBase :{}",username);
        }
        Collection<SimpleGrantedAuthority> authorities =new ArrayList<>();
        user.getRoles().forEach(role->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return  new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("saving a new  user {} to database",user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving a new  role {} to database",role.getName());

        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("add role {} to user {}",roleName,username);
        User user =userRepository.findByUsername(username);
        Role role =roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("find user {}",username);

        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("find all users");
        return userRepository.findAll();
    }

    @Override
    public int changePassword(String username,String oldPassword ,String newPassword) {
        log.info("change password of user {} from old: {}  to new :{}",username,oldPassword,newPassword);
        User user=userRepository.findByUsername(username);
        if (user.getPassword().equals(passwordEncoder.encode(oldPassword))){
            return 1;
        }else {
            return -1;
        }
    }

}
