package com.bat.man.cbm.security.filter;

import com.bat.man.cbm.jwt.JwtUser;
import com.bat.man.cbm.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemJwtAuthorizationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String jwt = JwtUtil.getJwt(request);
        if (!StringUtils.isEmpty(jwt)) {
            Claims claims = null;
            try {
                claims = JwtUtil.getClaims(jwt);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            }
            if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                JwtUser jwtUser = JwtUtil.getJwtUser(claims);
                if (jwtUser != null && jwtUser.getAuths() != null) {
                    String[] auths = jwtUser.getAuths();
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    for (String auth : auths) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(auth);
                        authorities.add(authority);
                    }
                    User user = new User(jwtUser.getUsername(), jwtUser.getUsername(), authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                if (JwtUtil.isTimeToRefresh(claims)) {
                    response.setHeader("Set-Cookie", JwtUtil.JWT_COOKIE_NAME + "=" + JwtUtil.refresh(jwt, true) + "; Path=/; HttpOnly");
                }
            }
        }
        chain.doFilter(request, response);
    }

}
