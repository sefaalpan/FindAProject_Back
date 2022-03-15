package be.ros.FindAProject.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {
    private String rue;
    private String numero;
    private String ville;
    private String codePostal;
    private String pays;
}
