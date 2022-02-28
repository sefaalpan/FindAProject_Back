package be.ros.FindAFreelance.controllers;

import be.ros.FindAFreelance.models.dtos.UserDto;
import be.ros.FindAFreelance.models.forms.UserLoginForm;
import be.ros.FindAFreelance.models.forms.UserRegisterForm;
import be.ros.FindAFreelance.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid UserLoginForm form){
        return ResponseEntity.ok().body(authService.login(form));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRegisterForm form){
        return ResponseEntity.ok().body(authService.register(form));
    }

    @GetMapping(path = "/is-logged")
    public boolean isLogged() {
        return true;
    }
}
