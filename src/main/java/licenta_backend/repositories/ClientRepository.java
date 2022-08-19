package licenta_backend.repositories;


import licenta_backend.entities.Client;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {




}
