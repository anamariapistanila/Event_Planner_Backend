package licenta_backend.repositories;

import licenta_backend.entities.OurWorkAdd;
import licenta_backend.entities.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OurWorkRepository extends JpaRepository<OurWorkAdd, Integer> {
}
