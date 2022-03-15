package be.ros.FindAProject.models.dtos;

import be.ros.FindAProject.models.entities.Adresse;
import be.ros.FindAProject.models.entities.Discriminator;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    Long id;

    String prenom;

    String nom;

    LocalDate naissance;

    String email;

    String username;

    String token;

    Discriminator discriminator;

    List<String> roles;

    Adresse adresse;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialsNonExpired;

    boolean enabled;


}