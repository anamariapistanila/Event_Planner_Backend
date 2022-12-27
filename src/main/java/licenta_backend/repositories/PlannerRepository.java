package licenta_backend.repositories;

import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import licenta_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlannerRepository extends JpaRepository<Planner, Integer> {


}
