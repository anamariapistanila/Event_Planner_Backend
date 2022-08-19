package licenta_backend.repositories;

import licenta_backend.entities.Comments;
import licenta_backend.entities.DetailsEvent;
import licenta_backend.entities.OurWorkAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailsRepository extends JpaRepository<DetailsEvent, Integer> {
    @Query(value = "select o from DetailsEvent o \n" +
            "where o.id_event= :id")
    List<DetailsEvent> findAllDetailsById(@Param("id") Integer id);
}
