package com.dmi.api.v1.user.service;

import com.dmi.api.v1.user.ui.model.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto iUser);
    UserDto getUserDetailsByEmail(String email);
}
