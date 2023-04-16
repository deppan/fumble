package com.tsinsi.auth.configuration.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    public static void response(HttpServletRequest request, HttpServletResponse response, int status, Map<String, Object> data) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        Map<String, Object> map = new HashMap<>();
        if (status != HttpStatus.OK.value()) {
            map.put("timestamp", Instant.now().toEpochMilli());
            map.put("url", request.getRequestURI());
        }
        if (data != null && !data.isEmpty()) {
            map.putAll(data);
        }
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(map));
        writer.flush();
        writer.close();
    }
}
