package com.dmi.security;

//@EnableWebSecurity
public class GatewaySecurityConfig{/* extends WebSecurityConfigurerAdapter {
    private final Environment environment;

    @Autowired
    public GatewaySecurityConfig(Environment environment){
        this.environment = environment;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.h2-console.url")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.users.registration.url")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.users.login.url")).permitAll()
                .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    */
}
