package com.tsinsi.fumble.account.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jodd.net.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Map;

public class AppAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.error403().status());

        Map<String, Object> map = Map.of("timestamp", Instant.now().toEpochMilli(), "message", "access denied");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(map));
        writer.flush();
        writer.close();
    }
}
