package be.ros.FindAProject.controllers;

import be.ros.FindAProject.models.dtos.UserDto;
import be.ros.FindAProject.models.forms.UserRegisterForm;
import be.ros.FindAProject.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDto> deleteOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody @Valid UserRegisterForm form) {
        return ResponseEntity.ok().body(service.insert(form));
    }

    @GetMapping(path = "/username")
    public ResponseEntity<UserDto> getOneByUsername(@RequestParam(value="username", required = false) String username) {
        return ResponseEntity.ok().body(service.findByUsername(username));
    }
    @GetMapping(path = "/byUsername")
    public ResponseEntity<UserDto> byUsername(@RequestParam(value="username", required = false) String username) {
        return ResponseEntity.ok().body(service.findByUsername(username));
    }
}
