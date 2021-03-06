package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ColoursController.class)
@AutoConfigureMockMvc
public class ColoursControllerTest {

    @MockBean
    private ColoursRepository coloursRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllCallsRepository() throws Exception {
        // Given
        given(coloursRepository.findAll()).willReturn(Collections.emptyList());

        // When
        mvc.perform(get("/Colours").accept(APPLICATION_JSON));

        // Then
        verify(coloursRepository).findAll();
    }

    @Test
    public void testGetAllReturnsOk() throws Exception {
            // Given
        given(coloursRepository.findAll()).willReturn(Collections.emptyList());

        // When
        ResultActions result = mvc.perform(get("/Colours").accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk());
    }

    @Test
    public void testGetAllReturnsEmptyList() throws Exception {
        // Given
        given(coloursRepository.findAll()).willReturn(Collections.emptyList());

        // When
        ResultActions result = mvc.perform(get("/Colours").accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json("[]"));
    }

    @Test
    public void testGetAllReturnsPeople() throws Exception {
        // Given
        given(coloursRepository.findAll()).willReturn(Arrays.asList(
            new Colour[]{new Colour(2001L, "Red"), new Colour(2002L, "Green"), new Colour(2003L, "Blue")}
        ));

        // When
        ResultActions result = mvc.perform(get("/Colours").accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json("["
                + "{'id':2001, 'name':'Red'},{'id':2002, 'name':'Green'}, {'id':2003, 'name':'Blue'}]"
            ));
    }
    
    // new tests
    
    @Test
    public void testGetReturnsNotFound() throws Exception {
            // Given
        given(coloursRepository.findById(anyLong())).willReturn(Optional.empty());

        // When
        ResultActions result = mvc.perform(get("/Colours/10").accept(APPLICATION_JSON));

        // Then
        result.andExpect(status().isNotFound());
    }

    @Test
    public void testGetReturnsPerson() throws Exception {
        // Given
        given(coloursRepository.findById(anyLong()))
                .willReturn(Optional.of(new Colour(1L, "Red")));

        // When
        ResultActions result = mvc.perform(get("/Colours/1").accept(APPLICATION_JSON));

        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json("{'id':1, 'name':'Red'}"));
    }
    
    @Test
    public void testSaveReturnsColour() throws Exception {
        final Colour colour = new Colour(4L, "Yellow");

        // Given
        given(coloursRepository.save(any())).willReturn(colour);

        // When
        ResultActions result = mvc.perform(
            post("/Colours")
            .content(
                "{\"id\":4,\"name\":\"Yellow\"}")
            .contentType(APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .accept(APPLICATION_JSON));     
        
        // Then
        result.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(content().json(
                "{'id':4, 'name':'Yellow'}"
            ));
    }
}