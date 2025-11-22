package com.cognizant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.user.controller.UserController;
import com.cognizant.user.dto.UserDto;
import com.cognizant.user.dto.UserResponseDto;
import com.cognizant.user.entities.User;
import com.cognizant.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class})

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto sampleUserDto;
    private UserResponseDto sampleResponseDto;

    @BeforeEach
    public void setUp() {
        sampleUserDto = new UserDto();
        sampleUserDto.setUserName("JohnDoe");

        sampleResponseDto = new UserResponseDto();
        sampleResponseDto.setUserId(1L);
        sampleResponseDto.setUserName("JohnDoe");
    }

    @Test
    public void testAddUser() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(1L);

        when(userService.addUser(any(UserDto.class))).thenReturn(mockUser);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    public void testFetchUserDetails() throws Exception {
        when(userService.fetchUserDetails(1L)).thenReturn(sampleResponseDto);

        mockMvc.perform(get("/api/fetch/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.userName").value("JohnDoe"));
    }

    @Test
    public void testUpdateDetails() throws Exception {
        sampleResponseDto.setUserName("UpdatedUser");

        when(userService.updateDetails(eq(1L), any(UserDto.class))).thenReturn(sampleResponseDto);

        mockMvc.perform(put("/api/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("UpdatedUser"));
    }



    @Test
    public void testGetUserById() throws Exception {
        sampleResponseDto.setUserName("Jane");

        when(userService.getUserById(1L)).thenReturn(sampleResponseDto);

        mockMvc.perform(get("/api/getUserById")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Jane"));
    }
}
