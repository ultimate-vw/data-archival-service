//package com.sumitsee.archival_service.controller;
//
//import com.sumitsee.archival_service.entity.security.AppUser;
//import com.sumitsee.archival_service.repository.security.AppUserRepository;
//import com.sumitsee.archival_service.security.CustomUserDetailsService;
//import com.sumitsee.archival_service.security.JwtUtil;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Disabled
//@WebMvcTest(AuthController.class)
//@AutoConfigureMockMvc(addFilters = false)
////class AuthControllerTest {
////
////
////    @Autowired
////    private MockMvc mockMvc;
////    @SuppressWarnings("removal")
////    @MockBean
////    private AuthenticationManager authManager;
////    @SuppressWarnings("removal")
////    @MockBean
////    private JwtUtil jwtUtil;
////    @SuppressWarnings("removal")
////    @MockBean
////    private CustomUserDetailsService userService;
////    @SuppressWarnings("removal")
////    @MockBean
////    private PasswordEncoder passwordEncoder;
////    @SuppressWarnings("removal")
////    @MockBean
////    private AppUserRepository userRepository;
////
////    @Test
////    void testRegister() throws Exception {
////        AppUser user = new AppUser(null, "testuser", "password", "User");
////
////        mockMvc.perform(post("/api/auth/register")
////                        .contentType("application/json")
////                        .content("""
////                        {
////                            "username": "testuser",
////                            "password": "password",
////                            "roles": "User"
////                        }
////                        """))
////                .andExpect(status().isOk())
////                .andExpect(content().string("User registered successfully"));
////    }
////
////    @Test
////    void testRegisterWithDuplicateUser() throws Exception {
////        AppUser existingUser = new AppUser(1L, "testuser", "password", "User");
////        when(userRepository.findById(anyString())).thenReturn(Optional.of(existingUser));
////
////        mockMvc.perform(post("/api/auth/register")
////                        .contentType("application/json")
////                        .content("""
////                                {
////                                    "username": "testuser",
////                                    "password": "password",
////                                    "roles": "User"
////                                }
////                                """))
////                .andExpect(status().isBadRequest())
////                .andExpect(content().string("User already exists"));
////    }
////
////    @Test
////    void testRegisterWithInvalidData() throws Exception {
////        mockMvc.perform(post("/api/auth/register")
////                        .contentType("application/json")
////                        .content("""
////                                {
////                                    "username": "",
////                                    "password": "",
////                                    "roles": "User"
////                                }
////                                """))
////                .andExpect(status().isBadRequest());
////    }
////
////    @Test
////    void testRegisterEncodesPassword() throws Exception {
////        AppUser user = new AppUser(null, "testuser", "password", "User");
////        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
////
////        mockMvc.perform(post("/api/auth/register")
////                .contentType("application/json")
////                .content("""
////                        {
////                            "username": "testuser",
////                            "password": "password",
////                            "roles": "User"
////                        }
////                        """));
////
////        verify(passwordEncoder).encode("password");
////        verify(userRepository).save(argThat(savedUser -> "encodedPassword".equals(savedUser.getPassword())));
////    }
//}