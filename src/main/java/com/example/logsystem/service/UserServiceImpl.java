package com.example.logsystem.service;

import com.example.logsystem.model.Role;
import com.example.logsystem.model.User;
import com.example.logsystem.repository.UserRepository;
import com.example.logsystem.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstname(), userRegistrationDto.getLastname(), userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Arrays.asList(new Role("Role_user")));

        return userRepository.save(user);
    }

}
