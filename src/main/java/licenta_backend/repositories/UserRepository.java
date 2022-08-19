package licenta_backend.repositories;

import licenta_backend.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {



    @Query(value = "SELECT p " +
            "FROM Users p " +
            "WHERE p.username = :username ")
    Optional<Users> findUserByUsername(@Param("username") String username);

    Users findByUsername(String username);
    Users findByEmail(String email);
}
