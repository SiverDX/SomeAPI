package de.cadentem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/rarities", "/supertypes", "/subtypes", "/types").permitAll()
                .antMatchers("/actuator").hasAuthority(SecurityPermission.ACTUATOR_READ.name())
                .antMatchers("/cards").hasAuthority(SecurityPermission.CARDS_READ.name())
                .anyRequest().authenticated().and().httpBasic();
    }
}
