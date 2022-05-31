package com.example.logsystem.service;

import com.example.logsystem.model.User;
import com.example.logsystem.web.dto.UserRegistrationDto;

public interface UserService {

    User save(UserRegistrationDto userRegistrationDto);

}
