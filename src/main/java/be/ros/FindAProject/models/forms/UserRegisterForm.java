package be.ros.FindAProject.models.forms;

import be.ros.FindAProject.contraints.PasswordConstraint;
import be.ros.FindAProject.models.entities.Discriminator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterForm {

    @NotNull @NotBlank @NotEmpty
    @Length(min = 3, max = 30)
    String username;

    @PasswordConstraint
    String password;

    @Email
    @Length(min = 3, max = 50)
    String email;

    @NotNull
    Discriminator discriminator;

    @NotNull @NotBlank @NotEmpty
    @Length(min = 3, max = 50)
    String nom;

    @NotNull @NotBlank @NotEmpty
    @Length(min = 3, max = 50)
    String prenom;

    @NotNull
    AdresseForm address;

    @NotNull
    LocalDate naissance;

}
