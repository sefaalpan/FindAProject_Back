package be.ros.FindAFreelance.services.impl;

import be.ros.FindAFreelance.mappers.impl.FreelanceMapper;
import be.ros.FindAFreelance.mappers.impl.ProjetMapper;
import be.ros.FindAFreelance.models.dtos.FreelanceDto;
import be.ros.FindAFreelance.models.dtos.ProjetDto;
import be.ros.FindAFreelance.models.entities.Freelance;
import be.ros.FindAFreelance.models.entities.Projet;
import be.ros.FindAFreelance.models.forms.UserRegisterForm;
import be.ros.FindAFreelance.repositories.FreelanceRepository;
import be.ros.FindAFreelance.repositories.ProjetRepository;
import be.ros.FindAFreelance.services.BaseService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreelanceServiceImpl implements BaseService<FreelanceDto, UserRegisterForm, Long> {
    private final FreelanceRepository repository;
    private final FreelanceMapper mapper;
    private final ProjetMapper projetMapper;
    private final ProjetRepository projetRepository;

    public FreelanceServiceImpl(FreelanceRepository repository, FreelanceMapper mapper, ProjetMapper projetMapper, ProjetRepository projetRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.projetMapper = projetMapper;
        this.projetRepository = projetRepository;
    }
//
//    public FreelanceServiceImpl(FreelanceRepository repository, FreelanceMapper mapper, ProjetMapper projetMapper) {
//        this.repository = repository;
//        this.mapper = mapper;
//        this.projetMapper = projetMapper;
//    }


    @Override
    public List<FreelanceDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FreelanceDto getOneById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public FreelanceDto insert(UserRegisterForm form) {
        return this.mapper.toDto(this.repository.save(this.mapper.formToEntity(form)));

    }

    @Override
    public FreelanceDto update(UserRegisterForm form, Long id) {
        return null;
    }

    @Override
    public FreelanceDto deleteById(Long id) {
//        Freelance f = repository.findById(id).orElse(null);
//        if(f==null)
//            return null;
//
//        f.getProjets().forEach(p->{
//            p.setCloture(true);
//            projetRepository.save(p);
//        });

        FreelanceDto dto = mapper.toDto(repository.findById(id).orElse(null));
        if (dto == null)
            return null;


        repository.deleteById(id);
        return dto;

    }

    public FreelanceDto findByUsername(String username) {
        return mapper.toDto(repository.findByUsername(username).orElse(null));
    }

//    public FreelanceDto addProjetToFreelance(ProjetDto projetDto, FreelanceDto freelanceDto) {
//        Freelance freelance = mapper.dtoToEntity(freelanceDto);
//        Projet projet = projetMapper.dtoToEntity(projetDto);
//
//        if (freelance != null && projet != null) {
//            freelance.addProjetToList(projet);
//            return mapper.toDto(repository.save(freelance));
//        }
//
//        return null;
//    }

    public FreelanceDto updateAddProjet(FreelanceDto dto) {
        Freelance f = this.mapper.dtoToEntity(dto);
        if (f != null) {
            return mapper.toDto(repository.save(f));
        }

        return null;
    }

    public FreelanceDto addProjet(FreelanceDto dto) {

        Freelance f = repository.findById(dto.getId()).orElse(null);
        if (f != null) {

            List<Projet> projetList = dto.getProjets().stream()
                    .map(projetMapper::dtoToEntity)
                    .collect(Collectors.toList());

            projetList.forEach(pDto -> {
                if (!f.getProjets().contains(pDto)) {
                    projetRepository.save(pDto);
                }
            });

            f.setProjets(
                    dto.getProjets().stream().map(projetMapper::dtoToEntity)
                            .collect(Collectors.toList())
            );
            return mapper.toDto(repository.save(f));
        }

        return null;
    }

    public List<ProjetDto> findProjetsByUsername(String username) {
        Freelance f = repository.findByUsername(username).orElse(null);

        if (f != null) {
            return f.getProjets().stream()
                    .map(projetMapper::toDto)
                    .collect(Collectors.toList());
        }

        return null;
    }
    public FreelanceDto addUpdateProjet(ProjetDto dto, Long id) {

        Freelance f = repository.findById(id).orElse(null);
        if(f==null)
            throw new RuntimeException("User not found");

        Projet p = projetMapper.dtoToEntity(dto);
        if(p == null)
            throw new RuntimeException("Project not found");

        if(p.getUser().getUsername() == f.getUsername())
            throw new RuntimeException("Vous ne pouvez pas développer vos propres projets");

        p.setReserve(true);
        p=projetRepository.save(p);
        f.addProjetToList(p);


        return mapper.toDto(repository.save(f));

    }

}
