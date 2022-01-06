package com.dmi.serucity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.Arrays;
import java.util.List;
import static com.dmi.serucity.AppUserPermission.*;
import static com.dmi.serucity.AppUserRoles.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> nonAuthenticatedUrls = Arrays.asList(
                "/","/css/*","/js/*"
        );
        http
                .csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeHttpRequests()
                .antMatchers("/","/css/**","/js/**").permitAll()
//                .antMatchers("/api/v1/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE, "/management/api/v1/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/v1/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/v1/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api/v1/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                //.anyRequest()
                //.authenticated()
                .and()
                //.httpBasic();
                .formLogin()
                .loginPage("/login")//.permitAll()
                .defaultSuccessUrl("/courses",true)
                .and()
                .rememberMe();
        //http.addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails johnUser = User.builder()
                                    .username("john")
                                    .password(passwordEncoder.encode("password"))
                                    //.roles(AppUserRoles.STUDENT.name())
                                    .authorities(STUDENT.getGrantedAuthoroties())
                                    .build();
        UserDetails benUser = User.builder()
                                    .username("ben")
                                    .password(passwordEncoder.encode("password"))
                                    //.roles(AppUserRoles.ADMIN.name())
                                    .authorities(ADMIN.getGrantedAuthoroties())
                                    .build();
        UserDetails tomUser = User.builder()
                                    .username("tom")
                                    .password(passwordEncoder.encode("password"))
                                    //.roles(AppUserRoles.ADMIN_TRAINEE.name())
                                    .authorities(ADMIN_TRAINEE.getGrantedAuthoroties())
                                    .build();
        return new InMemoryUserDetailsManager(
                johnUser,benUser,tomUser
        );
    }
}
