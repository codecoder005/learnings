package com.dmi.api.v1.user.service.impl;

import com.dmi.api.v1.user.persistance.UserRepository;
import com.dmi.api.v1.user.persistance.entity.UserEntity;
import com.dmi.api.v1.user.service.UserService;
import com.dmi.api.v1.user.ui.model.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserDto createUser(UserDto iUserDto) {
        iUserDto.setUserId(UUID.randomUUID().toString());
        iUserDto.setEncryptedPassword(passwordEncoder.encode(iUserDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(iUserDto, UserEntity.class);

        userRepository.save(userEntity);
        return iUserDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity == null) throw new UsernameNotFoundException("No user registered with that email: "+username);

        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true, true, true, true,
                new ArrayList<>());
    }
    @Override
    public UserDto getUserDetailsByEmail(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException("No user registered with that email: "+email);
        return new ModelMapper().map(userEntity, UserDto.class);
    }
}
