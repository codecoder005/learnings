package com.dmi.api.v1.account.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private Environment environment;
    @GetMapping
    public String ping(){
        return "This message is from Account management service running on port: "+environment.getProperty("local.server.port");
    }
}
