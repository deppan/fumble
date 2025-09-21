package com.tsinsi.console.adapter.in.security;

import com.nimbusds.jwt.JWTClaimsSet;
import com.tsinsi.console.application.in.JwtService;
import com.tsinsi.console.infrastructure.JwtProvider;
import com.tsinsi.console.infrastructure.Whitelist;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final JwtService jwtService;
    private final Whitelist whitelist;
    private final Enforcer enforcer;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, JwtService jwtService, Whitelist whitelist, Enforcer enforcer) {
        this.jwtProvider = jwtProvider;
        this.jwtService = jwtService;
        this.whitelist = whitelist;
        this.enforcer = enforcer;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (Arrays.asList(whitelist.getPaths()).contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<JWTClaimsSet> optional = jwtProvider.parse(authorization.split("Bearer ")[1]);
        if (optional.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        String uid = optional.get().getSubject();
        String jti = optional.get().getJWTID();
        if (!jwtService.isValidJti(uid, jti)) {
            filterChain.doFilter(request, response);
            return;
        }

        String method = request.getMethod();
        if (!enforcer.enforce("user:" + uid, path, method)) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("{\"code\":403,\"message\":\"Forbidden\"}");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(uid, null, List.of());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
