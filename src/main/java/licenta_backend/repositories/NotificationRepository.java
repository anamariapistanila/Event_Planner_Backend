package licenta_backend.repositories;

import licenta_backend.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query(value = "SELECT n FROM Notification n where n.toUser.id = :userId")
    List<Notification> findAllByToUser(@Param("userId") Integer userId);
}