package be.ros.FindAFreelance.repositories;

import be.ros.FindAFreelance.models.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ros.FindAFreelance.models.entities.Freelance;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreelanceRepository extends JpaRepository<Freelance, Long> {

    Optional<Freelance> findByUsername(String username);

}
