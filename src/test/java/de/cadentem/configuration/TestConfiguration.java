package de.cadentem.configuration;

import de.cadentem.assemblers.RarityAssembler;
import de.cadentem.assemblers.SubTypeAssembler;
import de.cadentem.assemblers.SuperTypeAssembler;
import de.cadentem.assemblers.TypeAssembler;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RarityAssembler.class, TypeAssembler.class, SubTypeAssembler.class, SuperTypeAssembler.class})
public class TestConfiguration {
    @MockBean
    @SuppressWarnings("unused")
    private RarityRepository rarityRepository;

    @MockBean
    @SuppressWarnings("unused")
    private SubTypeRepository subTypeRepository;

    @MockBean
    @SuppressWarnings("unused")
    private SupertypeRepository supertypeRepository;

    @MockBean
    @SuppressWarnings("unused")
    private TypeRepository typeRepository;
}
