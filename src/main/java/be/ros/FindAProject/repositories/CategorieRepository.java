package be.ros.FindAProject.repositories;

import be.ros.FindAProject.models.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Optional<Categorie> findByLabel(String label);

    List<Categorie> findByLabelContaining(String label);

    List<Categorie> findAllByOrderByLabelAsc();
}
