package com.sumitsee.archival_service.filter;

import com.sumitsee.archival_service.security.CustomUserDetailsService;
import com.sumitsee.archival_service.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Disabled

@WebMvcTest(JwtFilter.class)
class JwtFilterTest {

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @Test
    void shouldSetAuthenticationWhenValidJwtIsProvided() throws ServletException, IOException {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        String testToken = "valid.jwt.token";
        String username = "testUser";

        request.addHeader("Authorization", "Bearer " + testToken);

        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        when(jwtUtil.extractUsername(testToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(testToken, userDetails)).thenReturn(true);

        jwtFilter.doFilterInternal(request, response, chain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        UsernamePasswordAuthenticationToken authToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        assertNotNull(authToken);
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    void shouldNotSetAuthenticationWhenAuthorizationHeaderIsMissing() throws ServletException, IOException {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        jwtFilter.doFilterInternal(request, response, chain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    void shouldNotSetAuthenticationWhenJwtIsInvalid() throws ServletException, IOException {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        String testToken = "invalid.jwt.token";
        String username = "testUser";

        request.addHeader("Authorization", "Bearer " + testToken);

        when(jwtUtil.extractUsername(testToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(null);
        when(jwtUtil.validateToken(testToken, null)).thenReturn(false);

        jwtFilter.doFilterInternal(request, response, chain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    void shouldNotSetAuthenticationWhenExtractedUsernameIsNull() throws ServletException, IOException {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        String testToken = "jwt.token.without.username";

        request.addHeader("Authorization", "Bearer " + testToken);

        when(jwtUtil.extractUsername(testToken)).thenReturn(null);

        jwtFilter.doFilterInternal(request, response, chain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    void shouldNotSetAuthenticationWhenAuthenticationAlreadyExists() throws ServletException, IOException {
        JwtFilter jwtFilter = new JwtFilter(jwtUtil, userDetailsService);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        String testToken = "valid.jwt.token";
        String username = "testUser";

        request.addHeader("Authorization", "Bearer " + testToken);

        UserDetails userDetails = new User(username, "password", Collections.emptyList());
        UsernamePasswordAuthenticationToken existingAuthToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(existingAuthToken);

        jwtFilter.doFilterInternal(request, response, chain);

        verify(jwtUtil, never()).extractUsername(testToken);
        verify(jwtUtil, never()).validateToken(anyString(), any());
        verify(userDetailsService, never()).loadUserByUsername(anyString());

        verify(chain, times(1)).doFilter(request, response);
    }
}