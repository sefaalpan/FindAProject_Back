package be.ros.FindAFreelance.controllers;


import be.ros.FindAFreelance.models.dtos.ProjetDto;
import be.ros.FindAFreelance.models.forms.ProjetForm;
import be.ros.FindAFreelance.services.impl.ProjetServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/projets")
public class ProjetController {

    private final ProjetServiceImpl service;

    public ProjetController(ProjetServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProjetDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjetDto> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjetDto> deleteOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<ProjetDto> insert(/* Authentication auth, */ @RequestBody @Valid ProjetForm form) {
//        String username = (String) auth.getPrincipal();
        return ResponseEntity.ok().body(service.insert(form));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjetDto> update(@RequestBody @Valid ProjetForm form, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.update(form, id));
    }

    @GetMapping(path = "/byCategorie")
    public ResponseEntity<List<ProjetDto>> getProjectByCat(@RequestParam(value = "categorie_id", required = true) Long categorie_id) {
        return ResponseEntity.ok().body(service.findByCategorieId(categorie_id));
    }

    @GetMapping(path = "/byCatOrderByDateAsc")
    public ResponseEntity<List<ProjetDto>> getProjectByDateAsc(@RequestParam(value = "categorie_id", required = true) Long categorie_id) {
        return ResponseEntity.ok().body(service.findByCatIdOrderByDateAsc(categorie_id));
    }

    @GetMapping(path = "/byCatOrderByDateDesc")
    public ResponseEntity<List<ProjetDto>> getProjectByDateDesc(@RequestParam(value = "categorie_id", required = true) Long categorie_id) {
        return ResponseEntity.ok().body(service.findByCatIdOrderByDateDesc(categorie_id));
    }

    @GetMapping(path = "/byDateDesc")
    public ResponseEntity<List<ProjetDto>> getProjectByDateAsc() {
        return ResponseEntity.ok().body(service.findAllByDateDesc());
    }

    @GetMapping(path = "/byDateAsc")
    public ResponseEntity<List<ProjetDto>> getProjectByDateDesc() {
        return ResponseEntity.ok().body(service.findAllByDateAsc());
    }

    @GetMapping(path = "/byUsername")
    public ResponseEntity<List<ProjetDto>> getProjectByUsername(@RequestParam(value = "username") String username) {
        return ResponseEntity.ok().body(service.findByUserUsername(username));
    }

    @GetMapping(path = "/sortedAsc")
    public ResponseEntity<List<ProjetDto>> getProjectsSortedAsc() {
        return ResponseEntity.ok().body(service.findAllOrderByTitreAsc());
    }

    @GetMapping(path = "/sortedDesc")
    public ResponseEntity<List<ProjetDto>> getProjectsSortedDesc() {
        return ResponseEntity.ok().body(service.findAllOrderByTitreDesc());
    }

    @GetMapping(path = "/byReserveFalse")
    public ResponseEntity<List<ProjetDto>> getProjectsbyReserveFalse() {
        return ResponseEntity.ok().body(service.findAllByReserveFalse());
    }

    @GetMapping(path = "/byCatIdReserveFalse")
    public ResponseEntity<List<ProjetDto>> getProjectsbyCatIdReserveFalse(@RequestParam(value = "cat_id") Long id) {
        return ResponseEntity.ok().body(service.findByCategorieIdAndByReserveFalse(id));
    }

    @GetMapping(path = "/byOrderByDateAscAndReserveFalse")
    public ResponseEntity<List<ProjetDto>> getProjectsByOrderByDateAscAndReserveFalse() {
        return ResponseEntity.ok().body(service.findAllByOrderByDateAscAndByReserveFalse());
    }

    @GetMapping(path = "/byOrderByDateDescAndReserveFalse")
    public ResponseEntity<List<ProjetDto>> getProjectsByOrderByDateDescAndReserveFalse() {
        return ResponseEntity.ok().body(service.findAllByOrderByDateDescAndByReserveFalse());
    }


}
