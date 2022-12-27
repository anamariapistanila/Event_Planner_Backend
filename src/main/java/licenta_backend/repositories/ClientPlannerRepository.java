package licenta_backend.repositories;


import licenta_backend.entities.Client_Planner;
import licenta_backend.entities.CreateYourEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientPlannerRepository extends JpaRepository<Client_Planner, Integer> {
    @Query(value = "select o from Client_Planner o \n" +
            "where o.id_planner= :id")
    List<Client_Planner> findAllClientsForPlanner(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from Client_Planner o \n" +
            "where o.id_client= :id and o.id_planner= :id_planner")
    void deleteByIdClient(@Param("id") Integer id,int id_planner);


}
