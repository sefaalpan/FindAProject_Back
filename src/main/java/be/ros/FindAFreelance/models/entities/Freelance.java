package be.ros.FindAFreelance.models.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@DiscriminatorValue("FREELANCE")
public class Freelance extends User{

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="freelance_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Projet> projets = new ArrayList<>();

    public Freelance() {}

    public Freelance(String prenom, String nom, LocalDate naissance, String email, String username, String password, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        super(prenom, nom, naissance, email, username, password, roles, adresse, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }

    public Freelance(String prenom, String nom, LocalDate naissance, String email, String username, String password, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<Projet> projets) {
        super(prenom, nom, naissance, email, username, password, roles, adresse, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.projets = projets;
    }

    public Freelance(Long id, String prenom, String nom, LocalDate naissance, String email, String username, String password, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<Projet> projets) {
        super(id, prenom, nom, naissance, email, username, password, roles, adresse, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        this.projets = projets;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public void addProjetToList(Projet projet) {
        if(projet != null && !projets.contains(projet)){
            this.projets.add(projet);
        }
    }
}
