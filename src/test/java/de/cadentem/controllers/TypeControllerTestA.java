package de.cadentem.controllers;

import de.cadentem.entities.Rarity;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import de.cadentem.services.InitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TypeControllerTestA {
    @MockBean
    private RarityRepository rarityRepository;

    @MockBean
    private SubTypeRepository subTypeRepository;

    @MockBean
    private SupertypeRepository supertypeRepository;

    @MockBean
    private TypeRepository typeRepository;

    @MockBean
    // todo :: better way to do this? called by command line runner
    private InitService initService;

    @Autowired
    private TypeController typeController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
//        when(rarityRepository.findAll()).thenReturn(createRarities());
//        when(rarityRepository.findById(anyLong())).thenReturn(Optional.of(createRarity(1, "value-1")));
//        when(rarityRepository.findByValue(anyString())).thenReturn(Optional.of(createRarity(1, "value-1")));
    }

    @Test
    public void testRarity() throws Exception {
        when(rarityRepository.findByValue(anyString())).thenReturn(Optional.of(createRarity(1, "value-1")));

        mockMvc.perform(MockMvcRequestBuilders.get("/rarities/1"))
                .andDo(print()).andExpect(status().isOk());
//                .andExpect(content().string(contains("value-1")));

        // todo :: should actually call findById()?
        verify(rarityRepository).findByValue(anyString());
    }

    private List<Rarity> createRarities() {
        List<Rarity> rarities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Rarity rarity = createRarity(i, "value-" + i);
            rarities.add(rarity);
        }

        return rarities;
    }

    private Rarity createRarity(final long id, final String value) {
        // todo :: set random values if empty? some other way to do this?
        Rarity rarity = new Rarity();
        rarity.setId(id);
        rarity.setValue(value);

        return rarity;
    }
}
