package com.zubaray.userapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zubaray.userapi.config.JwtService;
import com.zubaray.userapi.entity.JwtRequest;
import com.zubaray.userapi.entity.UserApi;
import com.zubaray.userapi.repository.UserApiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ComponentScan("com.zubaray.userApi")
class JwtAuthenticationControllerTest {

    private MockMvc mvc;

    @MockBean
    private UserApiRepository repository;

    @MockBean
    private JwtService jwtService;

    private static String JSON_WEB_TOKEN = "json.web.token";

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(new JwtAuthenticationController(this.jwtService, this.repository)).build();
    }

    @Test
    void testCreateAuthenticationToken_ok() throws Exception {
        var user = UserApi.builder().id(1L).name("Jhon Doe").email("jhon@example.com").build();
        when(repository.findByEmailAndActiveTrue("jhon@example.com"))
                .thenReturn(Optional.of(user));
        when(jwtService.generateToken(Mockito.any()))
                .thenReturn(JSON_WEB_TOKEN);
        when(repository.save(Mockito.any())).thenReturn(user);

        JwtRequest request = new JwtRequest("jhon@example.com", "password");

        mvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwttoken", is(JSON_WEB_TOKEN)));
    }
}