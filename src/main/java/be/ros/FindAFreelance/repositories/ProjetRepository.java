package be.ros.FindAFreelance.repositories;

import be.ros.FindAFreelance.models.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    List<Projet> findByCategorieId(Long id);

    List<Projet> findByCategorieIdOrderByDateAsc(Long id);

    List<Projet> findByCategorieIdOrderByDateDesc(Long id);

    List<Projet> findAllByOrderByDateDesc();

    List<Projet> findAllByOrderByDateAsc();

    List<Projet> findByUserUsername(String username);

    List<Projet> findAllByOrderByTitreAsc();

    List<Projet> findAllByOrderByTitreDesc();

    List<Projet> findAllByReserveFalse();

    List<Projet> findByCategorieIdAndReserveFalse(Long id);

    List<Projet> findAllByReserveFalseOrderByDateAsc();

    List<Projet> findAllByReserveFalseOrderByDateDesc();


}
