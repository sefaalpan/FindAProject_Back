package be.ros.FindAFreelance.mappers.impl;

import be.ros.FindAFreelance.mappers.BaseMapper;
import be.ros.FindAFreelance.models.dtos.UserDto;
import be.ros.FindAFreelance.models.entities.Adresse;
import be.ros.FindAFreelance.models.entities.Discriminator;
import be.ros.FindAFreelance.models.entities.User;
import be.ros.FindAFreelance.models.forms.UserLoginForm;
import be.ros.FindAFreelance.models.forms.UserRegisterForm;
import be.ros.FindAFreelance.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserMapper implements BaseMapper<User, UserDto, UserRegisterForm> {

    private final PasswordEncoder encoder;

    //    private final ProjetMapper projetMapper;

//
//    public UserMapper(PasswordEncoder encoder, ProjetMapper projetMapper) {
//        this.encoder = encoder;
//        this.projetMapper = projetMapper;
//    }

    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDto toDto(User entity) {
        if (entity == null)
            return null;


//        FreelanceDto fDto = (FreelanceDto) UserDto
        UserDto uDto = UserDto.builder()
                .id(entity.getId())
                .prenom(entity.getPrenom())
                .nom(entity.getNom())
                .email(entity.getEmail())
                .naissance(entity.getNaissance())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .discriminator(Discriminator.CLIENT)
                .accountNonExpired(entity.isAccountNonExpired())
                .accountNonLocked(entity.isAccountNonLocked())
                .enabled(entity.isEnabled())
                .credentialsNonExpired(entity.isCredentialsNonExpired())
                .adresse(entity.getAdresse())
                .build();

//        if (entity instanceof Freelance) {
//            FreelanceDto fDto = (FreelanceDto) uDto;
//            fDto.setProjets(null);
////            fDto.setProjets(((Freelance) entity).getProjets().stream()
////                    .map(projetMapper::toDto)
////                    .collect(Collectors.toList()));
//            return fDto;
//        }

        return uDto;
    }


    @Override
    public User dtoToEntity(UserDto dto) {
        if (dto == null)
            return null;

        User client = new User();
        client.setId(dto.getId());
        client.setPrenom(dto.getPrenom());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        client.setNaissance(dto.getNaissance());
        client.setUsername(dto.getUsername());
        client.setAdresse(dto.getAdresse());
        client.setRoles(dto.getRoles());
        client.setAccountNonExpired(dto.isAccountNonExpired());
        client.setAccountNonLocked(dto.isAccountNonLocked());
        client.setEnabled(dto.isEnabled());
        client.setCredentialsNonExpired(dto.isCredentialsNonExpired());
        client.setNaissance(dto.getNaissance());

        return client;
    }

    @Override
    public User formToEntity(UserRegisterForm form) {
        if (form == null)
            return null;

//        if(service.loadUserByUsername(form.getUsername()) != null)
//            return null;


        User client = new User();
        client.setUsername(form.getUsername());
        client.setPassword(encoder.encode(form.getPassword()));
        client.setAdresse(
                Adresse.builder()
                        .pays(form.getAddress().getPays().toLowerCase())
                        .ville(form.getAddress().getVille().toLowerCase())
                        .rue(form.getAddress().getRue().toLowerCase())
                        .numero(form.getAddress().getNumero().toLowerCase())
                        .codePostal(form.getAddress().getCodePostal().toLowerCase())
                        .build());
        client.setEmail(form.getEmail().toLowerCase());
        client.setNom(form.getNom().toLowerCase());
        client.setPrenom(form.getPrenom().toLowerCase());
        client.setRoles(List.of("ROLE_USER"));
        client.setNaissance(form.getNaissance());
        client.setCredentialsNonExpired(true);
        client.setAccountNonLocked(true);
        client.setAccountNonExpired(true);
        client.setEnabled(true);

        return client;
    }

    public User formToEntity(UserLoginForm form) {
        if (form == null)
            return null;
        User client = new User();
        client.setUsername(form.getUsername());
        client.setPassword(encoder.encode(form.getPassword()));
        return client;
    }
}