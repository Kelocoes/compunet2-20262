package com.example.teacher.integration;

import com.example.teacher.model.Role;
import com.example.teacher.model.User;
import com.example.teacher.repository.IRoleRepository;
import com.example.teacher.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserIntegrationTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleRepository roleRepository;

    private User buildValidUser(String name, String email) {
        Role role = roleRepository.findById(2)
                .orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));

        return User.builder()
                .name(name)
                .email(email)
                .password("hashedpassword")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .role(role)
                .build();
    }

    @Test
    public void testCreate() {
        Role role = roleRepository.findById(1)
                .orElseGet(() -> roleRepository.save(Role.builder().name("ADMIN").build()));

        User user = User.builder()
                .name("newuser")
                .email("newuser-" + UUID.randomUUID() + "@example.com")
                .password("hashedpassword")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        User createdUser = userService.create(user, role.getId());

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("newuser", createdUser.getName());
        assertNotNull(createdUser.getRole());
        assertEquals(role.getId(), createdUser.getRole().getId());
    }

    @Test
    public void testFindAll() {
        String uniqueEmail = "findall-" + UUID.randomUUID() + "@example.com";
        User user = buildValidUser("findalluser", uniqueEmail);
        userService.create(user, user.getRole().getId());

        Page<User> usersPage = userService.findAll(0, 50, "id", "asc");

        assertNotNull(usersPage);
        assertFalse(usersPage.getContent().isEmpty());
        assertTrue(usersPage.getContent().stream().anyMatch(u -> uniqueEmail.equals(u.getEmail())));
    }
}
