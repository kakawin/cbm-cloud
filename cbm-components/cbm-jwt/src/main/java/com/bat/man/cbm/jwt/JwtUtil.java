package com.bat.man.cbm.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 重新定义issuer，当issuer等于该值，则为记住我
 */
public abstract class JwtUtil {

    /**
     * JWT在http请求中的header的参数名称；
     */
    public static final String JWT_HEADER_NAME = "_jwt";

    /**
     * JWT在http请求中的cookie的参数名称；
     */
    public static final String JWT_COOKIE_NAME = "_jwt";

    /**
     * JWT在http请求中的parameter的参数名称；
     */
    public static final String JWT_PARAM_NAME = "_jwt";

    /**
     * JWT的签名的字符串（非BASE64）
     */
    private static final String JWT_SIGNING_KEY_STRING = "com.bat.man.cbm";

    /**
     * JWT的签名的字符串（非BASE64）的字节码
     */
    private static final byte[] JWT_SIGNING_KEY_BYTES = JWT_SIGNING_KEY_STRING.getBytes();

    /**
     * 记住我的过期时长（毫秒）
     */
    public static final Long JWT_REMEMBER_ME_EXPIRATION_MILLIS = 30 * 24 * 60 * 60 * 1000L;//30天

    /**
     * 记住我的刷新JWT过期时长（毫秒）
     */
    private static final Long JWT_REMEMBER_ME_TO_REFRESH_MILLIS = 3 * 24 * 60 * 60 * 1000L;//3天

    /**
     * 默认的过期时长（毫秒）
     */
    public static final Long JWT_DEFAULT_EXPIRATION_MILLIS = 30 * 60 * 1000L;//30分钟

    /**
     * 默认的刷新JWT过期时长（毫秒）
     */
    private static final Long JWT_DEFAULT_TO_REFRESH_MILLIS = 3 * 60 * 1000L;//3分钟

    /**
     * 重新定义issuer，当issuer等于该值，则为记住我
     */
    private static final String JWT_ISSUER_REMEMBER_ME = "reme";

    /**
     * JWT的用户信息key
     */
    private static final String JWT_USER_KEY = "user";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getJwt(HttpServletRequest request) {
        String jwt = request.getHeader(JWT_HEADER_NAME);
        if (StringUtils.isEmpty(jwt)) {
            jwt = request.getParameter(JWT_PARAM_NAME);
        }
        if (StringUtils.isEmpty(jwt)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (JWT_COOKIE_NAME.equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
        }
        return jwt;
    }

    public static String createJwt(JwtUser jwtUser, boolean isRememberMe) {
        final Claims claims = new DefaultClaims();
        if (isRememberMe) {
            claims.setIssuer(JWT_ISSUER_REMEMBER_ME);
        }
        final Date created = new Date();
        final Date expired = calculateExpiration(created, isRememberMe);
        claims.setIssuedAt(created);
        claims.setExpiration(expired);
        claims.put(JWT_USER_KEY, jwtUser);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SIGNING_KEY_BYTES)
                .compact();
    }

    public static Claims getClaims(String jwt) {
        return Jwts.parser().setSigningKey(JWT_SIGNING_KEY_BYTES).parseClaimsJws(jwt).getBody();
    }

    public static JwtUser getJwtUser(String jwt) {
        return getJwtUser(getClaims(jwt));
    }

    public static JwtUser getJwtUser(Claims claims) {
        Object user = claims.get(JWT_USER_KEY);
        try {
            return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(user), JwtUser.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static String refresh(String jwt, boolean force) {
        final Claims claims = getClaims(jwt);
        if (!force && !isTimeToRefresh(claims)) {
            return jwt;
        }
        final Date created = new Date();
        final Date expired = calculateExpiration(created, isRememberMe(claims));
        claims.setIssuedAt(created);
        claims.setExpiration(expired);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SIGNING_KEY_BYTES)
                .compact();
    }

    private static Date calculateExpiration(Date created, boolean isRememberMe) {
        return new Date(created.getTime() + (isRememberMe ? JWT_REMEMBER_ME_EXPIRATION_MILLIS : JWT_DEFAULT_EXPIRATION_MILLIS));
    }

    private static boolean isRememberMe(Claims claims) {
        return JWT_ISSUER_REMEMBER_ME.equals(claims.getIssuer());
    }

    public static boolean isTimeToRefresh(Claims claims) {
        long created = claims.getIssuedAt().getTime();
        long current = System.currentTimeMillis();
        if (isRememberMe(claims) && (created + JWT_REMEMBER_ME_TO_REFRESH_MILLIS < current)) {
            return true;
        } else if (created + JWT_DEFAULT_TO_REFRESH_MILLIS < current) {
            return true;
        }
        return false;
    }

}
