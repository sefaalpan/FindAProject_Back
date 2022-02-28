package be.ros.FindAFreelance.mappers.impl;

import be.ros.FindAFreelance.mappers.BaseMapper;
import be.ros.FindAFreelance.models.dtos.FreelanceDto;
import be.ros.FindAFreelance.models.entities.Adresse;
import be.ros.FindAFreelance.models.entities.Discriminator;
import be.ros.FindAFreelance.models.entities.Freelance;
import be.ros.FindAFreelance.models.forms.UserLoginForm;
import be.ros.FindAFreelance.models.forms.UserRegisterForm;
import be.ros.FindAFreelance.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreelanceMapper implements BaseMapper<Freelance, FreelanceDto, UserLoginForm> {
    private final ProjetMapper projetMapper;
    private final PasswordEncoder encoder;


    public FreelanceMapper(ProjetMapper projetMapper, PasswordEncoder encoder) {
        this.projetMapper = projetMapper;
        this.encoder = encoder;
    }

    @Override
    public FreelanceDto toDto(Freelance entity) {
        if (entity == null)
            return null;

        FreelanceDto dto = new FreelanceDto();
        dto.setId(entity.getId());
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());
        dto.setEmail(entity.getEmail());
        dto.setDiscriminator(Discriminator.FREELANCE);
        dto.setNaissance(entity.getNaissance());
        dto.setUsername(entity.getUsername());
        dto.setProjets(entity.getProjets().stream().map(projetMapper::toDto).collect(Collectors.toList()));
        dto.setAdresse(entity.getAdresse());
        dto.setRoles(entity.getRoles());
        dto.setAccountNonExpired(entity.isAccountNonExpired());
        dto.setAccountNonLocked(entity.isAccountNonLocked());
        dto.setEnabled(entity.isEnabled());
        dto.setCredentialsNonExpired(entity.isCredentialsNonExpired());
        dto.setNaissance(entity.getNaissance());

        return dto;
    }

    @Override
    public Freelance dtoToEntity(FreelanceDto dto) {
        if (dto == null)
            return null;

        Freelance freelance = new Freelance();
        freelance.setId(dto.getId());
        freelance.setPrenom(dto.getPrenom());
        freelance.setNom(dto.getNom());
        freelance.setEmail(dto.getEmail());
        freelance.setNaissance(dto.getNaissance());
        freelance.setUsername(dto.getUsername());
        freelance.setProjets(dto.getProjets().stream().map(projetMapper::dtoToEntity).collect(Collectors.toList()));
        freelance.setAdresse(dto.getAdresse());
        freelance.setRoles(dto.getRoles());
        freelance.setAccountNonExpired(dto.isAccountNonExpired());
        freelance.setAccountNonLocked(dto.isAccountNonLocked());
        freelance.setEnabled(dto.isEnabled());
        freelance.setCredentialsNonExpired(dto.isCredentialsNonExpired());
        freelance.setNaissance(dto.getNaissance());

        return freelance;
    }

    @Override
    public Freelance formToEntity(UserLoginForm form) {
        if (form == null)
            return null;
        Freelance freelance = new Freelance();
        freelance.setUsername(form.getUsername());
        freelance.setPassword(encoder.encode(form.getPassword()));
        return freelance;
    }

    public Freelance formToEntity(UserRegisterForm form) {
        if (form == null) return null;

        Freelance freelance = new Freelance();
        freelance.setUsername(form.getUsername());
        freelance.setPassword(encoder.encode(form.getPassword()));
        freelance.setAdresse(
                Adresse.builder()
                        .pays(form.getAddress().getPays())
                        .ville(form.getAddress().getVille())
                        .rue(form.getAddress().getRue())
                        .numero(form.getAddress().getNumero())
                        .codePostal(form.getAddress().getCodePostal())
                        .build());
        freelance.setEmail(form.getEmail());
        freelance.setNom(form.getNom());
        freelance.setPrenom(form.getPrenom());
        freelance.setRoles(List.of("ROLE_FREELANCE"));
        freelance.setNaissance(form.getNaissance());
        freelance.setCredentialsNonExpired(true);
        freelance.setAccountNonLocked(true);
        freelance.setAccountNonExpired(true);
        freelance.setEnabled(true);

        return freelance;
    }
}
