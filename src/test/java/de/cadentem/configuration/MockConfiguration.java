package de.cadentem.configuration;

import de.cadentem.assemblers.SubTypeAssembler;
import de.cadentem.assemblers.SuperTypeAssembler;
import de.cadentem.assemblers.TypeAssembler;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import de.cadentem.security.PasswordConfiguration;
import de.cadentem.security.SecurityRole;
import de.cadentem.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Import(PasswordConfiguration.class)
public class MockConfiguration {
    private final PasswordEncoder encoder;

    public MockConfiguration(final PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @MockBean
    // todo :: better way to do this? called by command line runner
    private InitService initService;

    @MockBean
    private TypeAssembler typeAssembler;

    @MockBean
    private SubTypeAssembler subTypeAssembler;

    @MockBean
    private SuperTypeAssembler superTypeAssembler;

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("test-user")
                .password(encoder.encode("test-password"))
                .authorities(SecurityRole.CONSUMER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

