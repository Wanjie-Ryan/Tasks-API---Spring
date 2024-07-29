package com.tasks.api.projects.configs;

import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.repository.AuthRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

//    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthRepository authRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {


        final String header = req.getHeader("Authorization");
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.replace("Bearer ", "");
            if (authToken != null && authToken != "") {
                try {
                    username = jwtTokenUtil.getUsernameFromToken(authToken);
                    log.info("Found username {} ", username);
                } catch (final IllegalArgumentException e) {
                    logger.error("an error occurred during getting username from token {}", e.getCause());
                } catch (final ExpiredJwtException e) {
                    logger.info("User token has expired");
                    username = jwtTokenUtil.getUsernameUnlimitedSkew(authToken);
                    final Optional<Auth> user = authRepo.findByEmail(username);
                    username = null;
                }
            }
        } else {
            // logger.warn("couldn't find bearer string, will ignore the header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                // Role role = roleService.getUserRole(userDetails.getUsername());
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(req, res);






    }
}
