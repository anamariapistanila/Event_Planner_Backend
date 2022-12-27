package licenta_backend.repositories;

import licenta_backend.entities.CreateYourEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreateYourEventRepository extends JpaRepository<CreateYourEvent, Integer> {

    @Query(value = "select o from CreateYourEvent o \n" +
            "where o.createYourEvent.id= :id")
    List<CreateYourEvent> findAllForDisEvent(@Param("id") Integer id);


    @Query(value = "select o from CreateYourEvent o \n" +
            "where o.id_client= :id")
    List<CreateYourEvent> findAllEventsClient(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from CreateYourEvent o \n" +
            "where o.id= :id")
    void deleteByIdEvent(@Param("id") Integer id);

}
