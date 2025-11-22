package com.cognizant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognizant.user.dto.UserDto;
import com.cognizant.user.dto.UserResponseDto;
import com.cognizant.user.entities.RoleStatus;
import com.cognizant.user.entities.User;
import com.cognizant.user.entities.UserPreference;
import com.cognizant.user.exception.UserNotFoundException;
import com.cognizant.user.repositories.UserRepository;
import com.cognizant.user.serviceimpl.UserServiceImpl;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser_ShouldReturnSavedUser() {
        UserDto userDto = new UserDto();
        userDto.setUserName("John");

        User user = new User();
        user.setUserId(1L);
        user.setUserName("John");
        user.setRole(RoleStatus.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setPrefernce(new UserPreference());

        when(userRepo.save(any(User.class))).thenReturn(user);

        User result = userService.addUser(userDto);

        assertNotNull(result);
        assertEquals("John", result.getUserName());
        assertEquals(RoleStatus.USER, result.getRole());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testFetchUserDetails_ShouldReturnUserResponseDto() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("Alice");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto responseDto = userService.fetchUserDetails(1L);

        assertNotNull(responseDto);
        assertEquals(1L, responseDto.getUserId());
        assertEquals("Alice", responseDto.getUserName());
    }

    @Test
    public void testFetchUserDetails_ShouldThrowExceptionIfNotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.fetchUserDetails(1L));
    }

    @Test
    public void testUpdateDetails_ShouldReturnUpdatedUserResponseDto() {
        UserDto userDto = new UserDto();
        userDto.setUserName("UpdatedName");

        User user = new User();
        user.setUserId(1L);
        user.setUserName("OldName");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenReturn(user);

        UserResponseDto updated = userService.updateDetails(1L, userDto);

        assertNotNull(updated);
        assertEquals(1L, updated.getUserId());
    }

    @Test
    public void testDeleteDetails_ShouldReturnTrue() {
        User user = new User();
        user.setUserId(1L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        boolean result = userService.deleteDetails(1L);

        assertTrue(result);
        verify(userRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteDetails_ShouldThrowExceptionIfNotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteDetails(1L));
    }

    @Test
    public void testGetUserById_ShouldReturnUserResponseDto() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("Charlie");

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto dto = userService.getUserById(1L);

        assertNotNull(dto);
        assertEquals("Charlie", dto.getUserName());
    }

    @Test
    public void testGetUserById_ShouldThrowExceptionIfNotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }
}