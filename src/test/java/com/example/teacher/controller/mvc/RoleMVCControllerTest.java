package com.example.teacher.controller.mvc;

import com.example.teacher.model.Role;
import com.example.teacher.service.IRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RoleMVCControllerTest {

    @Mock
    private IRoleService roleService;

    @InjectMocks
    private RoleMVCController roleMVCController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleMVCController).build();
    }

    @Test
    void testGetAllRoles() throws Exception {
        when(roleService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/mvc/roles"))
                .andExpect(status().isOk())
                .andExpect(view().name("roles/list"))
                .andExpect(model().attributeExists("roles"));

        verify(roleService, times(1)).findAll();
    }

    @Test
    void testAddRoleForm() throws Exception {
        mockMvc.perform(get("/mvc/roles/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("roles/add"))
                .andExpect(model().attributeExists("role"));
    }

    @Test
    void testAddRoleSubmit() throws Exception {
        when(roleService.create(any(Role.class))).thenReturn(new Role());

        mockMvc.perform(post("/mvc/roles/add")
                        .param("name", "ROLE_ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/roles"));

        verify(roleService, times(1)).create(any(Role.class));
    }

    @Test
    void testEditRoleForm() throws Exception {
        Role role = Role.builder().id(1).name("ROLE_USER").build();
        when(roleService.findById(1)).thenReturn(Optional.of(role));

        mockMvc.perform(get("/mvc/roles/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("roles/edit"))
                .andExpect(model().attributeExists("actualRole"));

        verify(roleService, times(1)).findById(1);
    }

    @Test
    void testEditRoleSubmit() throws Exception {
        when(roleService.update(eq(1), any(Role.class))).thenReturn(new Role());

        mockMvc.perform(post("/mvc/roles/edit/1")
                        .param("name", "ROLE_MODERATOR"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/roles"));

        verify(roleService, times(1)).update(eq(1), any(Role.class));
    }

    @Test
    void testDeleteRole() throws Exception {
        doNothing().when(roleService).deleteById(1);

        mockMvc.perform(get("/mvc/roles/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/roles"));

        verify(roleService, times(1)).deleteById(1);
    }
}
