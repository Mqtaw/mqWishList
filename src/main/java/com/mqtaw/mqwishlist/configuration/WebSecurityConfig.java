package com.mqtaw.mqwishlist.configuration;

import com.mqtaw.mqwishlist.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/registration", "/login").permitAll()
                .anyRequest().permitAll()
                        .and()
                .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                        .and()
                .logout()
                        .permitAll();
    }

    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((request) -> request
//                    .antMatchers("/").permitAll()
//                    .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                    .loginPage("/login")
//                    .permitAll()
//        ).logout((logout) -> logout.permitAll());


//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests()
//                    .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
//                    .antMatchers("/hr_info").hasRole("HR")
//                    .antMatchers("/manager_info").hasRole("MANAGER")
//                    .and().formLogin().permitAll();

//        return  http.build();
//    }

//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(user);



}
