package com.app.hospitalmanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.app.hospitalmanagementsystem.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/hms/user/**").hasRole(PATIENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/postlogin", true)
                .and()
                .rememberMe()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("adminhms")
                .password(passwordEncoder.encode("adminhms"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails doctor = User.builder()
                .username("doctorhms")
                .password(passwordEncoder.encode("doctorhms"))
                .roles(DOCTOR.name())
                .authorities(DOCTOR.getGrantedAuthorities())
                .build();

        UserDetails patient = User.builder()
                .username("patienthms")
                .password(passwordEncoder.encode("patienthms"))
                .roles(PATIENT.name())
                .authorities(PATIENT.getGrantedAuthorities())
                .build();

        UserDetails receptionist = User.builder()
                .username("receptionisthms")
                .password(passwordEncoder.encode("receptionisthms"))
                .roles(RECEPTIONIST.name())
                .authorities(RECEPTIONIST.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                doctor,
                patient,
                receptionist
        );
    }
}
