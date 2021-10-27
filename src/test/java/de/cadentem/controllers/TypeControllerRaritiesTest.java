package de.cadentem.controllers;

import de.cadentem.configuration.TestConfiguration;
import de.cadentem.entities.Rarity;
import de.cadentem.repositories.RarityRepository;
import de.cadentem.services.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfiguration.class, TypeService.class})
@WebMvcTest(controllers = TypeController.class)
public class TypeControllerRaritiesTest {

    @Autowired
    private RarityRepository rarityRepository;

    @Autowired
    private MockMvc mockMvc;

    @EmptySource
    @ParameterizedTest
    @ValueSource(strings = {"someRarity", "#ä049gji4", "_~~~|", "   ", "\t", "\n"})
    public void test_get_200_findByValue(final String value) throws Exception {
        when(rarityRepository.findByValue(value)).thenReturn(createRarities(value));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rarities")
                        .param("value", value))
                .andDo(print());

        verify(rarityRepository).findByValue(value);

        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$._embedded.rarities[0].id").value("0"));
        response.andExpect(jsonPath("$._embedded.rarities[0].value").value(value));

        // todo :: can this be generalised?
        String linkPath = "$._embedded.rarities[0]._links";

        response.andExpect(jsonPath(linkPath + ".self.href").value("http://localhost/rarities/0"));
        response.andExpect(jsonPath(linkPath + ".rarities.href").value("http://localhost/rarities"));

        response.andExpect(jsonPath("$._links.self.href").value("http://localhost/rarities"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"someRarity"})
    public void test_get_400_wrongParameter(final String value) throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rarities")
                        .param("someWeirdThing", value))
                .andDo(print());

        response.andExpect(status().isBadRequest());
    }

    @NullSource
    @ParameterizedTest
    public void test_get_400_null(final String value) throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rarities")
                        .param("value", value))
                .andDo(print());

        response.andExpect(status().isBadRequest());
    }

    @Test
    public void test_get_200_findAll() throws Exception {
        int amount = 10;

        when(rarityRepository.findAll()).thenReturn(createRarities(amount));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/rarities"))
                .andDo(print());

        verify(rarityRepository).findAll();

        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$._embedded.rarities", hasSize(amount)));
    }

    private List<Rarity> createRarities(int amount) {
        List<Rarity> rarities = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Rarity rarity = createRarity(i, UUID.randomUUID().toString());
            rarities.add(rarity);
        }

        return rarities;
    }

    private List<Rarity> createRarities(final String value) {
        Rarity rarity = createRarity(0, value);

        List<Rarity> rarities = new ArrayList<>();
        rarities.add(rarity);

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
