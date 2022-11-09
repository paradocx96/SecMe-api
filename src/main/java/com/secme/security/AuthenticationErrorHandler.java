/*
 * SSD - SecMe API
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 */
package com.secme.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secme.model.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Authentication Error Handler for Spring Security
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 *
 * */
@Component
@RequiredArgsConstructor
public class AuthenticationErrorHandler implements AuthenticationEntryPoint {

    // Define ObjectMapper for JSON object mapping
    private final ObjectMapper mapper;

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException
    ) throws IOException, ServletException {
        // Define Error Message
        String message = String.format("Unauthorized. %s", authException.getMessage());

        // Set Response Message
        ErrorMessage errorMessage = ErrorMessage.from(message);
        String json = mapper.writeValueAsString(errorMessage);

        // Set Response Values
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(json);
        response.flushBuffer();
    }
}
