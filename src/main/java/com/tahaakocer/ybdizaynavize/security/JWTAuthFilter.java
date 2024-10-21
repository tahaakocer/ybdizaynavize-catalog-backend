package com.tahaakocer.ybdizaynavize.security;

import com.tahaakocer.ybdizaynavize.exception.user.TokenInvalidException;
import com.tahaakocer.ybdizaynavize.exception.user.TokenMissingException;
import com.tahaakocer.ybdizaynavize.service.user.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@Log4j2
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JWTAuthFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwt = parseJwt(request);
            if (jwt != null && this.jwtService.validateToken(jwt)) {
                String username = this.jwtService.extractUsername(jwt);
                log.debug("username {}", username);

                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (MalformedJwtException
                 | ExpiredJwtException
                 | UnsupportedJwtException
                 | IllegalArgumentException
                 | TokenInvalidException ex) {
            log.error("JWT validation failed: ", ex);
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, ex); // Bu satır ile hata yanıtı dönülür
            return; // Hata durumunda işleme devam etmiyoruz
        } catch (Exception ex) {
            log.error("Unexpected error: ", ex);
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, ex); // İç hata durumu döndürülür
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, Exception ex) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + ex.getMessage() + "\"}");
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        } else {
            return null;

        }
    }

}