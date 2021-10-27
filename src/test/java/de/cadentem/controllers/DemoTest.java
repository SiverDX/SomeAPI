package de.cadentem.controllers;

import de.cadentem.entities.Rarity;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.repositories.SubTypeRepository;
import de.cadentem.repositories.SupertypeRepository;
import de.cadentem.repositories.TypeRepository;
import de.cadentem.services.InitService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class DemoTest {
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
    private MockMvc mockMvc;

    @Test
    public void testRarity() throws Exception {
        String[] values = new String[]{"someRarity"};

        when(rarityRepository.findByValue(values[0])).thenReturn(createRarities(values));

        mockMvc.perform(MockMvcRequestBuilders.get("/rarities").param("value", values[0]))
                .andDo(print())
                .andExpect(status().isOk());

        verify(rarityRepository).findByValue(values[0]);

        // todo :: not sure if one should bother checkin this
        verify(initService).initTypes();
        verify(initService).initSubTypes();
        verify(initService).initSuperTypes();
        verify(initService).initRarities();
    }

    private List<Rarity> createRarities(final String[] values) {
        List<Rarity> rarities = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            Rarity rarity = createRarity(i, values[i]);
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
