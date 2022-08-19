package licenta_backend.repositories;

import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import licenta_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Integer> {

}
