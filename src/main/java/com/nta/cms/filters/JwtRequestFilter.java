package com.nta.cms.filters;

import com.nta.cms.constants.JwtConstant;
import com.nta.cms.data.MyUserDetails;
import com.nta.cms.data.http.resp.ApiResponse;
import com.nta.cms.exceptions.AuthorizationException;
import com.nta.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nta.cms.services.user.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtConstant.JWT_HEADER);
        if (header != null && header.startsWith(JwtConstant.JWT_TOKEN_PREFIX)) {
            String token = header.substring(JwtConstant.JWT_TOKEN_PREFIX.length());
            String username;
            try {
                username = JwtUtils.extractUsername(token);
            } catch (JwtException e) {
                AuthorizationException exception = new AuthorizationException(e.getMessage());
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(mapper.writeValueAsString(new ApiResponse(exception.getStatus().value(), exception.getMessage())));
                return;
            }
            MyUserDetails myUserDetails;
            try {
                myUserDetails = (MyUserDetails) userService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                AuthorizationException exception = new AuthorizationException("User not found");
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(mapper.writeValueAsString(new ApiResponse(exception.getStatus().value(), exception.getMessage())));
                return;
            }


            if (myUserDetails != null && JwtUtils.validateToken(token, myUserDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
