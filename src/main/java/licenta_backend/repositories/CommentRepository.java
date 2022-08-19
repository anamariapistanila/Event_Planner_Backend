package licenta_backend.repositories;

import licenta_backend.entities.Client;
import licenta_backend.entities.Comments;
import licenta_backend.entities.CreateYourEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

    @Query(value = "select o from Comments o \n" +
            "where o.events.id= :id")
    List<Comments> findAllCommentsById(@Param("id") Integer id);
}
