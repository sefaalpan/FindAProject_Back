package be.ros.FindAProject.services.impl;

import be.ros.FindAProject.mappers.impl.CategorieMapper;
import be.ros.FindAProject.mappers.impl.ProjetMapper;
import be.ros.FindAProject.models.dtos.ProjetDto;
import be.ros.FindAProject.models.entities.Projet;
import be.ros.FindAProject.models.forms.ProjetForm;
import be.ros.FindAProject.repositories.ProjetRepository;
import be.ros.FindAProject.services.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetServiceImpl implements BaseService<ProjetDto, ProjetForm, Long> {
    private final ProjetRepository repository;
    private final ProjetMapper mapper;
    private final CategorieMapper categorieMapper;

    public ProjetServiceImpl(ProjetRepository repository, ProjetMapper mapper, CategorieMapper categorieMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.categorieMapper = categorieMapper;
    }


    @Override
    public List<ProjetDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjetDto getOneById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public ProjetDto insert(ProjetForm form) {
        return mapper.toDto(repository.save(mapper.formToEntity(form)));
    }

    @Override
    public ProjetDto update(ProjetForm form, Long id) {
        Projet projet = repository.findById(id).orElse(null);
        if (projet == null)
            return null;

        projet.setCategorie(categorieMapper.formToEntity(form.getCategorie()));
        projet.setPrix(form.getPrix());
        projet.setDate(form.getDate());
        projet.setTitre(form.getTitre());
        projet.setResume(form.getResume());

        return mapper.toDto(repository.save(projet));
    }

    @Override
    public ProjetDto deleteById(Long id) {
        ProjetDto dto = mapper.toDto(repository.findById(id).orElse(null));
        if (dto == null)
            return null;

        repository.deleteById(id);
        return dto;
    }

    public List<ProjetDto> findByCategorieId(Long id) {
        return repository.findByCategorieId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public List<ProjetDto> findByCatIdOrderByDateAsc(Long id) {
        return repository.findByCategorieIdOrderByDateAsc(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findByCatIdOrderByDateDesc(Long id) {
        return repository.findByCategorieIdOrderByDateDesc(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllByDateAsc() {
        return repository.findAllByOrderByDateAsc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllByDateDesc() {
        return repository.findAllByOrderByDateDesc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findByUserUsername(String username) {
        return repository.findByUserUsername(username)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllOrderByTitreAsc() {
        return repository.findAllByOrderByTitreAsc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllOrderByTitreDesc() {
        return repository.findAllByOrderByTitreDesc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllByReserveFalse() {
        return repository.findAllByReserveFalse()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findByCategorieIdAndByReserveFalse(Long id) {
        return repository.findByCategorieIdAndReserveFalse(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllByOrderByDateAscAndByReserveFalse() {
        return repository.findAllByReserveFalseOrderByDateAsc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjetDto> findAllByOrderByDateDescAndByReserveFalse() {
        return repository.findAllByReserveFalseOrderByDateDesc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


}
