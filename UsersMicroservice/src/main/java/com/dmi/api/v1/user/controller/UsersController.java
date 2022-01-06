package com.dmi.api.v1.user.controller;

import com.dmi.api.v1.user.response.model.CreateUserResponseModel;
import com.dmi.api.v1.user.service.UserService;
import com.dmi.api.v1.user.ui.model.CreateUserRequestModel;
import com.dmi.api.v1.user.ui.model.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private Environment environment;
    @Autowired
    private UserService userService;
    @GetMapping("/status/check")
    public String status(){
        StringBuilder message = new StringBuilder("Hello... This message is from Users-Microservice: instance is running on ")
                            .append(environment.getProperty("local.server.port"));
        return message.toString();
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel iUser){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(iUser, UserDto.class);
        UserDto responseUserDto = userService.createUser(userDto);
        CreateUserResponseModel responseModel = modelMapper.map(responseUserDto, CreateUserResponseModel.class);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }
}
