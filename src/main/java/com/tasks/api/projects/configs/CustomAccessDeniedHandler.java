package com.tasks.api.projects.configs;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

//    private static final Logger logger = Logger.getLogger(CustomAccessDeniedHandler.class.getName());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
//        logger.info("Forbidden error: " + accessDeniedException.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println("{ \"error\": \"Forbidden: You don't have permission to access this resource\" }");
    }
}
