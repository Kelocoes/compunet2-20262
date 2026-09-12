package com.example.teacher.mock;

import com.example.teacher.model.Role;
import com.example.teacher.model.User;
import com.example.teacher.repository.IUserRepository;
import com.example.teacher.service.IRoleService;
import com.example.teacher.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IRoleService roleService;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private User savedUser;
    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
        role.setId(1);
        role.setName("ADMIN");

        user = new User();
        user.setId(null);
        user.setName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("hashedpassword");
        user.setCreatedAt(Timestamp.valueOf("2023-01-01 00:00:00"));
        user.setRole(role);

        savedUser = new User();
        savedUser.setId(10);
        savedUser.setName("testuser");
        savedUser.setEmail("testuser@example.com");
        savedUser.setPassword("hashedpassword");
        savedUser.setCreatedAt(Timestamp.valueOf("2023-01-01 00:00:00"));
        savedUser.setRole(role);
    }

    @Test
    public void testCreate() {
        when(roleService.findById(1)).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.create(user, 1);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("testuser", result.getName());
        assertEquals(role, result.getRole());
        verify(roleService, times(1)).findById(1);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindAll() {
        List<User> userList = Arrays.asList(savedUser);
        Page<User> userPage = new PageImpl<>(userList);

        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        Page<User> result = userService.findAll(0, 10, "id", "asc");

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("testuser", result.getContent().get(0).getName());
        verify(userRepository, times(1)).findAll(any(Pageable.class));
    }
}
