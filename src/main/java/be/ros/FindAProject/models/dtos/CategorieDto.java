package be.ros.FindAProject.models.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategorieDto {

    Long id;

    String label;
}
