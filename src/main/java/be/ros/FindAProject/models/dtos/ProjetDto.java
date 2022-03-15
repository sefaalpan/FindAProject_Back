package be.ros.FindAProject.models.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjetDto {

    Long projet_id;

    String titre;

    String resume;

    LocalDate date;

    double prix;

    CategorieDto categorie;

//    UserDto user;

    String username;

    boolean reserve;

}
