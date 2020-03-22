package com.kvark900;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kvark900.api.controller.AuthorController;
import com.kvark900.api.model.Author;
import com.kvark900.api.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Keno&Kemo on 03.03.2018..
 */

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class TestAuthorController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private JacksonTester<Author> authorJacksonTester;


    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void testGetAllAuthorsWhenExist() throws Exception {
        Author author = new Author();
        author.setName("Richard");
        author.setSurname("Feynman");

        List<Author> allAuthors = Collections.singletonList(author);

        given(authorService.findAll()).willReturn(allAuthors);

        mockMvc.perform(get("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllAuthorsWhenNotFound() throws Exception {
        List<Author> allAuthors = null;

        given(authorService.findAll()).willReturn(allAuthors);

        mockMvc.perform(get("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllAuthorsWhenEmpty() throws Exception {
        List<Author> allAuthors = new ArrayList<>();

        given(authorService.findAll()).willReturn(allAuthors);

        mockMvc.perform(get("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testSaveAuthor() throws Exception {
        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/authors").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(
                        authorJacksonTester.write(new Author("Richard", "Feynman")).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}
