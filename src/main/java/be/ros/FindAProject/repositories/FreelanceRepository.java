package be.ros.FindAProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.ros.FindAProject.models.entities.Freelance;

import java.util.Optional;

@Repository
public interface FreelanceRepository extends JpaRepository<Freelance, Long> {

    Optional<Freelance> findByUsername(String username);

}
