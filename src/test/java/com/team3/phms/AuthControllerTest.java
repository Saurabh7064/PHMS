package com.team3.phms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // Register
    // TC1: Positive case, return user info and 200
    @Test
    public void SignUpTest() throws Exception {
        String json = "{\"username\":\"test1112311\",\"password\":\"test123123\",\"email\":\"test112323@gmail.com\",\"role\":[\"user\",\"admin\"],\"gender\":\"\",\"age\":\"\",\"weight\":\"\",\"height\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andDo(MockMvcResultHandlers.print());
    }

    // TC2: UserID less than 5 characters is provided, return 500
    @Test
    public void SignUpUsernameTest() throws Exception {
        String json = "{\"username\":\"t\",\"password\":\"test123123\",\"email\":\"test@gmail.com\",\"role\":[\"user\",\"admin\"],\"gender\":\"\",\"age\":\"\",\"weight\":\"\",\"height\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    // TC3: Duplicate username, return "Error: Username is already in use!" and 500
    @Test
    public void SignUpDuplicateNameTest() throws Exception {
        String json = "{\"username\":\"test2\",\"password\":\"test123123\",\"email\":\"test@gmail.com\",\"role\":[\"user\",\"admin\"],\"gender\":\"\",\"age\":\"\",\"weight\":\"\",\"height\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Error: Username is already taken!"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    // TC3: Duplicate email, return "Error: Email is already in use!" and 500
    @Test
    public void SignUpDuplicateEmailTest() throws Exception {
        String json = "{\"username\":\"test111111\",\"password\":\"test123123\",\"email\":\"test@gmail.com\",\"role\":[\"user\",\"admin\"],\"gender\":\"\",\"age\":\"\",\"weight\":\"\",\"height\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Error: Email is already in use!"))
                .andDo(MockMvcResultHandlers.print());
    }

    // TC7: Incorrect email format, return 500
    @Test
    public void SignUpEmailTest() throws Exception {
        String json = "{\"username\":\"t\",\"password\":\"t\",\"email\":\"test@gmail\",\"role\":[\"user\",\"admin\"],\"gender\":\"\",\"age\":\"\",\"weight\":\"\",\"height\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(500))
                .andDo(MockMvcResultHandlers.print());
    }

    // Login
    // TC1: Enter correct username and password, return token and 200
    @Test
    public void LoginTest() throws Exception {
        String json = "{\"username\":\"test3\",\"password\":\"test123123\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    // TC2: Enter wrong username or password, return "Bad credentials" and 500
    @Test
    public void LoginWrongUsernameOrPwdTest() throws Exception {
        String json = "{\"username\":\"test3\",\"password\":\"test1231123\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad credentials"))
                .andDo(MockMvcResultHandlers.print());
    }

    // TC3: Empty username or password, return 500
    @Test
    public void LoginEmptyTest() throws Exception {
        String json = "{\"username\":\"\",\"password\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(500))
                .andDo(MockMvcResultHandlers.print());
    }
}