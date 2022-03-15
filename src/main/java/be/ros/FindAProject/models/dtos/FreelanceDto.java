package be.ros.FindAProject.models.dtos;

import be.ros.FindAProject.models.entities.Adresse;
import be.ros.FindAProject.models.entities.Discriminator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FreelanceDto extends UserDto {

    List<ProjetDto> projets;

    public FreelanceDto() {}

    public FreelanceDto(Long id, String prenom, String nom, LocalDate naissance, String email, String username, String token, Discriminator discriminator, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<ProjetDto> projets) {
        super(id, prenom, nom, naissance, email, username, token, discriminator, roles, adresse, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.projets = projets;
    }

    public FreelanceDto(Long id, String prenom, String nom, LocalDate naissance, String email, String username, String token, Discriminator discriminator, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        super(id, prenom, nom, naissance, email, username, token, discriminator, roles, adresse, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }

    public FreelanceDto(List<ProjetDto> projets) {
        this.projets = projets;
    }
}
