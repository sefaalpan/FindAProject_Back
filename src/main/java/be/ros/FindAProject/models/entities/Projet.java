package be.ros.FindAProject.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long projet_id;

    @Column(nullable = false)
    String titre;

    @Column(nullable = false, length = 10000)
    String resume;

    @Column(nullable = false)
    LocalDate date;

    @Column(nullable = false)
    double prix;

    @ManyToOne(targetEntity = Categorie.class)
    @JoinColumn(name = "categorie_id")
    Categorie categorie;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    User user;

    boolean reserve;



}
