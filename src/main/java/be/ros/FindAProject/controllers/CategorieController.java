package be.ros.FindAProject.controllers;

import be.ros.FindAProject.models.dtos.CategorieDto;
import be.ros.FindAProject.models.forms.CategorieForm;
import be.ros.FindAProject.services.impl.CategorieServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategorieController {

    private final CategorieServiceImpl service;

    public CategorieController(CategorieServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategorieDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategorieDto> getOneById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getOneById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CategorieDto> deleteOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<CategorieDto> insert(@RequestBody @Valid CategorieForm form) {
        return ResponseEntity.ok().body(service.insert(form));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategorieDto> update(@RequestBody @Valid CategorieForm form, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.update(form, id));
    }

    @GetMapping(path = "/byLabel")
    public ResponseEntity<CategorieDto> getbylabel(
            @RequestParam(value = "label") String label
    ) {
        return ResponseEntity.ok().body(service.findByLabel(label));
    }

    @GetMapping(path = "/allByLabel")
    public ResponseEntity<List<CategorieDto>> getAllbylabell(
            @RequestParam(value = "label") String label
    ) {
        return ResponseEntity.ok().body(service.findByLabelContaining(label));
    }

}
