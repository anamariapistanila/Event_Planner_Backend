package licenta_backend.repositories;

import licenta_backend.entities.Admin;
import licenta_backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
