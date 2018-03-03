package com.kvark900;

import com.kvark900.api.controller.AuthorController;
import com.kvark900.api.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Keno&Kemo on 03.03.2018..
 */

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class TestControllers {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;


    @Test
    public void testController() throws Exception{
        mockMvc.perform(get("/authors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }




}
