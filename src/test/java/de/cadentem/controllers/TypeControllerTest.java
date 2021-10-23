package de.cadentem.controllers;

import de.cadentem.entities.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TypeController.class)
public class TypeControllerTest {
    @MockBean
    private TypeController typeController;

    @Autowired
    private MockMvc mockMvc;

    // todo :: put somewhere else
    private final int[] ids = new int[]{0, 1, 2, 3};
    private final String[] values = new String[]{"one", "two", "three", "four"};

    @BeforeEach
    public void setUp() {
//        when(typeController.getTypes()).thenReturn(createResponse());
    }

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(get("/types"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(createBody(ids, values).toString()));

        verify(typeController).getTypes();
    }

    private CollectionModel<ResponseEntity<EntityModel<Type>>> createResponse() {
        assert ids.length == values.length;

        List<Type> types = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            Type type = new Type(values[i]);
            type.setId(ids[i]);

            types.add(type);
        }


//        return ResponseEntity.ok();
        return null;
    }

    private JSONArray createBody(final int[] ids, final String[] values) throws JSONException {
        assert ids.length == values.length;

        JSONArray elements = new JSONArray();

        for (int i = 0; i < ids.length; i++) {
            JSONObject element = creatElement(ids[i], values[i]);

            elements.put(i, element);
        }

        return elements;
    }

    private JSONObject creatElement(final int id, final String value) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("value", value);

        return object;
    }
}

