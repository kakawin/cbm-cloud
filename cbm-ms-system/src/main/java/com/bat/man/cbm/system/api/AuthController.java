package com.bat.man.cbm.system.api;

import com.bat.man.cbm.jwt.JwtUser;
import com.bat.man.cbm.jwt.JwtUtil;
import com.bat.man.cbm.rsa.RSAPublicKey;
import com.bat.man.cbm.rsa.service.RSAKeyService;
import com.bat.man.cbm.security.domain.AuthUser;
import com.bat.man.cbm.security.exception.PasswordException;
import com.bat.man.cbm.security.exception.SignatureException;
import com.bat.man.cbm.security.exception.UsernameException;
import com.bat.man.cbm.security.util.PasswordManager;
import com.bat.man.cbm.system.domain.User;
import com.bat.man.cbm.system.domain.dto.JwtObject;
import com.bat.man.cbm.system.domain.dto.LoginRequest;
import com.bat.man.cbm.system.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    RSAKeyService rsaKeyService;

    @PostMapping("/logout")
    ResponseEntity<Boolean> logout() {
        return ResponseEntity.ok()
                .header("Set-Cookie", JwtUtil.getExpiredJwtCookie())
                .body(true);
    }

    @GetMapping("/getPublicKey")
    ResponseEntity<RSAPublicKey> getPublicKey() throws Exception {
        RSAPublicKey publicKey = rsaKeyService.getPublicKey();
        return ResponseEntity.ok(publicKey);
    }

    @PostMapping("/login")
    ResponseEntity<JwtObject> login(LoginRequest loginRequest) {
        LoginRequest loginUser;
        try {
            String decryptJson = rsaKeyService.decrypt(loginRequest.getUsername(), loginRequest.getPassword());
            loginUser = new ObjectMapper().readValue(decryptJson, new TypeReference<LoginRequest>() {
            });
            loginUser.setReme(loginRequest.isReme());
        } catch (Exception e) {
            throw new SignatureException("登陆签名验证失败");
        }
        User user = userService.getOneByUsername(loginUser.getUsername());
        if (user == null) {
            throw new UsernameException("帐号或密码错误");
        }
        if (!PasswordManager.matches(loginUser.getPassword(), user.getPassword())) {
            throw new PasswordException("帐号或密码错误");
        }
        JwtUser jwtUser = new JwtUser(user.getId(), user.getUsername(), user.getNickname(), new String[]{AuthUser.ROLE_ADMIN});
        String jwt = JwtUtil.createJwt(jwtUser, loginUser.isReme());

        return ResponseEntity.ok()
                .header("Set-Cookie", JwtUtil.getJwtCookie(jwt, loginUser.isReme()))
                .body(new JwtObject(jwt));
    }

    @PostMapping("/password")
    ResponseEntity<String> password(@RequestParam String password) {
        return ResponseEntity.ok(PasswordManager.encode(password));
    }

}
