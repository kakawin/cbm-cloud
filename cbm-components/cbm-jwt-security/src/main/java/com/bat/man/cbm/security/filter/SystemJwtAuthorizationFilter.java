package com.bat.man.cbm.security.filter;

import com.bat.man.cbm.jwt.JwtUser;
import com.bat.man.cbm.jwt.JwtUtil;
import com.bat.man.cbm.security.domain.AuthUser;
import com.bat.man.cbm.security.util.AuthUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
                    Set<String> authSet = new LinkedHashSet<>();
                    for (String auth : auths) {
                        if (auth != null && auth.length() > 0) {
                            authSet.add(auth);
                        }
                    }
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    for (String auth : authSet) {
                        authorities.add(new SimpleGrantedAuthority(auth));
                    }
                    AuthUser authUser = new AuthUser(jwtUser, JwtUtil.isRememberMe(claims));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authUser, jwt, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    AuthUtil.setAuth(authentication);
                }
                if (JwtUtil.isTimeToRefresh(claims)) {
                    response.setHeader("Set-Cookie", JwtUtil.getJwtCookie(JwtUtil.refresh(jwt, true), JwtUtil.isRememberMe(claims)));
                }
            }
        }
        chain.doFilter(request, response);
    }

}
