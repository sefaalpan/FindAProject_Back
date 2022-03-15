package be.ros.FindAProject.models.forms;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdresseForm {

    @NotNull @NotBlank @NotEmpty
    String rue;

    @NotNull @NotBlank @NotEmpty
    String numero;

    @NotNull @NotBlank @NotEmpty
    String codePostal;

    @NotNull @NotBlank @NotEmpty
    String ville;

    @NotNull @NotBlank @NotEmpty
    String pays;



}