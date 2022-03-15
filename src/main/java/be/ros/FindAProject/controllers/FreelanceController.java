package be.ros.FindAProject.controllers;

import be.ros.FindAProject.models.dtos.FreelanceDto;
import be.ros.FindAProject.models.dtos.ProjetDto;
import be.ros.FindAProject.models.entities.User;
import be.ros.FindAProject.models.forms.UserRegisterForm;
import be.ros.FindAProject.services.impl.FreelanceServiceImpl;
import be.ros.FindAProject.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/freelances")
public class FreelanceController {

    private final UserServiceImpl userService;
    private final FreelanceServiceImpl freelanceService;

    public FreelanceController(UserServiceImpl userService, FreelanceServiceImpl freelanceService) {
        this.userService = userService;
        this.freelanceService = freelanceService;
    }

    @GetMapping(path = "/user")
    public ResponseEntity<User> getone(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok().body((User) userService.loadUserByUsername(username));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FreelanceDto> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok().body(freelanceService.getOneById(id));
    }

    @GetMapping
    public ResponseEntity<List<FreelanceDto>> getAll() {
        return ResponseEntity.ok().body(freelanceService.getAll());
    }


    @PostMapping
    public ResponseEntity<FreelanceDto> insert(@RequestBody @Valid UserRegisterForm form) {
        return ResponseEntity.ok().body(freelanceService.insert(form));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<FreelanceDto> deleteOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(freelanceService.deleteById(id));
    }

    @GetMapping(path = "/byUsername")
    public ResponseEntity<FreelanceDto> getOneByUsername(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok().body(freelanceService.findByUsername(username));
    }

    @PostMapping(path = "/addProjet")
    public ResponseEntity<FreelanceDto> addProjet(@RequestBody @Valid FreelanceDto dto) {
        return ResponseEntity.ok().body(freelanceService.addProjet(dto));
    }

    @GetMapping(path = "/getProjets")
    public ResponseEntity<List<ProjetDto>> getProjets(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok().body(freelanceService.findProjetsByUsername(username));
    }


    @PutMapping(path = "/addUpdateProjet/{id}")
    public ResponseEntity<FreelanceDto> addupdateProjet(@RequestBody @Valid ProjetDto projetDto, @PathVariable Long id) {
        return ResponseEntity.ok().body(freelanceService.addUpdateProjet(projetDto, id));
    }

}
