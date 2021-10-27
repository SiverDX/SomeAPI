package de.cadentem.controllers;

import de.cadentem.repositories.CardRepository;
import de.cadentem.security.PasswordConfiguration;
import de.cadentem.security.SecurityConfiguration;
import de.cadentem.services.CardService;
import de.cadentem.utils.EntityCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfiguration.class, CardService.class, PasswordConfiguration.class})
@WebMvcTest(controllers = CardController.class)
public class CardControllerTestSecurity {
    @MockBean
    private CardRepository cardRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_get_401_cards_noUser() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/cards"))
                .andDo(print());

        response.andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(value = "consumer", authorities = "SOME_THING")
    public void test_get_403_cards_missingAuthorities() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/cards"))
                .andDo(print());

        response.andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "consumer", authorities = "CARDS_READ")
    public void test_get_200_cards_hasAuthorities() throws Exception {
        int amount = 10;

        when(cardRepository.findAll()).thenReturn(EntityCreator.createCards(amount));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/cards"))
                .andDo(print());

        verify(cardRepository).findAll();

        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$", hasSize(amount)));
    }
}
