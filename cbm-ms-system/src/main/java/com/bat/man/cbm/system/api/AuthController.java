package com.bat.man.cbm.system.api;

import com.bat.man.cbm.jwt.JwtUser;
import com.bat.man.cbm.jwt.JwtUtil;
import com.bat.man.cbm.rsa.RSAPublicKey;
import com.bat.man.cbm.rsa.service.RSAKeyService;
import com.bat.man.cbm.security.exception.PasswordException;
import com.bat.man.cbm.security.exception.SignatureException;
import com.bat.man.cbm.security.exception.UsernameException;
import com.bat.man.cbm.system.domain.User;
import com.bat.man.cbm.system.domain.dto.JwtDto;
import com.bat.man.cbm.system.domain.dto.LoginUserDto;
import com.bat.man.cbm.system.service.UserService;
import com.bat.man.cbm.system.util.PasswordManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    RSAKeyService rsaKeyService;

    @GetMapping("/logout")
    ResponseEntity<Boolean> logout() {
        return ResponseEntity.ok()
                .header("Set-Cookie", JwtUtil.JWT_COOKIE_NAME + "=; Path=/; Max-Age=0; HttpOnly")
                .body(true);
    }

    @GetMapping("/loginKey")
    ResponseEntity<RSAPublicKey> loginKey() throws Exception {
        RSAPublicKey publicKey = rsaKeyService.getPublicKey();
        return ResponseEntity.ok(publicKey);
    }

    @PostMapping("/login")
    ResponseEntity<JwtDto> login(LoginUserDto loginUserDto) {
        LoginUserDto loginUser;
        try {
            String decryptJson = rsaKeyService.decrypt(loginUserDto.getUsername(), loginUserDto.getPassword());
            loginUser = new ObjectMapper().readValue(decryptJson, new TypeReference<LoginUserDto>() {
            });
            loginUser.setReme(loginUserDto.isReme());
        } catch (Exception e) {
            throw new SignatureException("登陆签名验证失败");
        }
        User dbUser = userService.getOneByUsername(loginUser.getUsername());
        if (dbUser == null) {
            throw new UsernameException("帐号或密码错误");
        }
        if (!PasswordManager.matches(loginUser.getPassword(), dbUser.getPassword())) {
            throw new PasswordException("帐号或密码错误");
        }
        JwtUser jwtUser = new JwtUser(dbUser.getId(), dbUser.getUsername(), dbUser.getNickname(), new String[]{"system-user-view"});
        String jwt = JwtUtil.createJwt(jwtUser, loginUser.isReme());

        long maxAge = (loginUser.isReme() ? JwtUtil.JWT_REMEMBER_ME_EXPIRATION_MILLIS : JwtUtil.JWT_DEFAULT_EXPIRATION_MILLIS) / 1000;
        return ResponseEntity.ok()
                .header("Set-Cookie", JwtUtil.JWT_COOKIE_NAME + "=" + jwt + "; Path=/; Max-Age=" + maxAge + "; HttpOnly")
                .body(new JwtDto(jwt));
    }

}
