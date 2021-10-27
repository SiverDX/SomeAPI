package de.cadentem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder encoder;

    public SecurityConfiguration(final PasswordEncoder encoder) {
        this.encoder = encoder;
    }

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

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(encoder.encode("password"))
                .authorities(SecurityRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails dummy = User.builder()
                .username("consumer")
                .password(encoder.encode("123"))
                .authorities(SecurityRole.CONSUMER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user, dummy);
    }
}
