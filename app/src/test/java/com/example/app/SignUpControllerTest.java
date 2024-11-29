package com.example.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class SignUpControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private SignUpController signUpController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(signUpController).build();
    }

    // Test for the signUpPage method
    @Test
    public void testSignUpPage() throws Exception {
        mockMvc.perform(get("/signup/sign-up"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("signUp");
    }
}