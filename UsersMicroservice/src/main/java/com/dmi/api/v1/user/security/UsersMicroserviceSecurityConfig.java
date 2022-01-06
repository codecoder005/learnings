package com.dmi.api.v1.user.security;

import com.dmi.api.v1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class UsersMicroserviceSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${api.gateway.ip-address}")
    private String gatewayIpAddress;
    private UserService userService;
    private Environment environment;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UsersMicroserviceSecurityConfig(
            UserService userService,
            Environment environment,
            PasswordEncoder bCryptPasswordEncoder
    ){
        this.userService = userService;
        this.environment = environment;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/api/**" ).permitAll()//.hasIpAddress(gatewayIpAddress)
                .and()
                .addFilter(getAuthenticationFilter());//.formLogin();
    }

    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment, authenticationManager());
        //authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
