package be.ros.FindAFreelance.services.impl;

import be.ros.FindAFreelance.mappers.impl.CategorieMapper;
import be.ros.FindAFreelance.models.dtos.CategorieDto;
import be.ros.FindAFreelance.models.entities.Categorie;
import be.ros.FindAFreelance.models.forms.CategorieForm;
import be.ros.FindAFreelance.repositories.CategorieRepository;
import be.ros.FindAFreelance.services.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements BaseService<CategorieDto, CategorieForm, Long> {

    private final CategorieRepository repository;
    private final CategorieMapper mapper;

    public CategorieServiceImpl(CategorieRepository repository, CategorieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CategorieDto> getAll() {
        return repository.findAllByOrderByLabelAsc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDto getOneById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));

    }

    @Override
    public CategorieDto insert(CategorieForm form) {
        Categorie c = repository.findByLabel(form.getLabel()).orElse(null);
        if (c != null)
            return mapper.toDto(c);

        return mapper.toDto(repository.save(mapper.formToEntity(form)));
    }

    @Override
    public CategorieDto update(CategorieForm form, Long id) {

        if(repository.findByLabel(form.getLabel()).isPresent())
            return null;

        Categorie categorie = repository.findById(id).orElse(null);

        if (categorie == null)
            return null;

        categorie.setLabel(form.getLabel());

        return mapper.toDto(repository.save(categorie));
    }

    @Override
    public CategorieDto deleteById(Long id) {
        CategorieDto dto = mapper.toDto(repository.findById(id).orElse(null));
        if (dto == null)
            return null;

        repository.deleteById(id);
        return dto;
    }

    public CategorieDto findByLabel(String label) {
        return mapper.toDto(repository.findByLabel(label).orElse(null));
    }

    public List<CategorieDto> findByLabelContaining(String label){
        return repository.findByLabelContaining(label)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
