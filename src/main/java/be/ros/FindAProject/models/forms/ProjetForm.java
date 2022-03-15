package be.ros.FindAProject.models.forms;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Validated
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjetForm {

    @NotNull @NotBlank @NotEmpty
    @Length(max = 254)
    String titre;

    @NotNull @NotBlank @NotEmpty
    @Length(max = 10000)
    String resume;

    @NotNull
    LocalDate date;

    @Min(10)
    double prix;

    @NotNull
    CategorieForm categorie;

    @NotNull
    UserSimpleForm userSimpleForm;
}
