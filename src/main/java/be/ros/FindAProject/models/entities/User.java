package be.ros.FindAProject.models.entities;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Table
@Entity
@Data
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String prenom;

    @Column(nullable = false)
    protected String nom;

    @Column(nullable = false)
    protected LocalDate naissance;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

//    @Column(nullable = false)
//    protected String role;

    @ElementCollection(fetch = FetchType.EAGER)
    protected List<String> roles;

    @Embedded
    protected Adresse adresse;

    protected boolean accountNonExpired;
    protected boolean accountNonLocked;
    protected boolean credentialsNonExpired;
    protected boolean enabled;

    public User() {    }

    public User(String prenom, String nom, LocalDate naissance, String email, String username, String password, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.prenom = prenom;
        this.nom = nom;
        this.naissance = naissance;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.adresse = adresse;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public User(Long id, String prenom, String nom, LocalDate naissance, String email, String username, String password, List<String> roles, Adresse adresse, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.naissance = naissance;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.adresse = adresse;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (role == null)
//            return null;
//
//        return role.lines()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());

        return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


    }

    @Transient
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

}
