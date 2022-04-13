package com.numeratorx.usersmanagement.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationUserDetailService neokredUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(neokredUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests().antMatchers("/neokred/admin").permitAll()
                .and().authorizeRequests().antMatchers("/ppi/v1/jit/**").permitAll()
                .and().authorizeRequests().antMatchers("/ppi/v1/customer/**").permitAll()
                .and().authorizeRequests().antMatchers("/neokred/admin/corporate/**").authenticated().and().httpBasic()
                .and().authorizeRequests().antMatchers("/corporate/admin/**").authenticated().and().httpBasic()
                .and().authorizeRequests().antMatchers("/ppi/v1/developer/**").authenticated().and().httpBasic();
        //.and().addFilterBefore(customHeaderFilter(), UsernamePasswordAuthenticationFilter.class).authorizeRequests().antMatchers("/ppi/v1").permitAll();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

