package com.example.logsystem.service;

import com.example.logsystem.model.User;
import com.example.logsystem.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);

}
