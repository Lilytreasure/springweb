package com.example.logsystem.service;

import com.example.logsystem.model.Role;
import com.example.logsystem.model.User;
import com.example.logsystem.repository.UserRepository;
import com.example.logsystem.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;

    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstname(),
                userRegistrationDto.getLastname(), userRegistrationDto.getEmail(),
               passwordEncoder.encode( userRegistrationDto.getPassword()),
                Arrays.asList(new Role("Role_user")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if(user ==null){
            throw  new UsernameNotFoundException("invalid username/password");
        }
        //gets username and password
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }
    private Collection<? extends GrantedAuthority>  mapRolesToAuthorities(Collection<Role> roles){
        //Lambda filter and extract data from a collection
        return  roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());


    }




}
