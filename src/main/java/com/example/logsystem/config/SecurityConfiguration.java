package com.example.logsystem.config;


import com.example.logsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity

//websecurityconfigureadapter not working
// New version of spring security do not support webSecurityConfiguration adapter
//Save password in the database using the Bcrypt password encoder // dont save the password in a raw form
public class SecurityConfiguration   {

    // The new implementation instead of using the web security configurerAdapter
    @Autowired
    public  UserService userService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider aoAuthenticationProvider(){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return  auth;

    }

    protected  void Configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(aoAuthenticationProvider());
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//read on the function of this
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        //Allow other pages to load beside spring security default log in page
                                        //Use two asterisk to allow other pages to load as well
                                        .antMatchers("/registration/**","/css/**","/js/**","/img/**")
                                        .permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .formLogin()
                                        .loginPage("/login")
                                        .permitAll()
                                        .and()
                                        .logout()
                                        .invalidateHttpSession(true)
                                        .clearAuthentication(true)
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                        //use a / to allow the pages to load else will throw an error to use the slash or begin with https
                                        .logoutSuccessUrl("/login?logout")
                                        .permitAll();


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }


                )
                .httpBasic(withDefaults());
        return http.build();


    }

    



}
