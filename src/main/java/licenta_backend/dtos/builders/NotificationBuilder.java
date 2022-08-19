package licenta_backend.dtos.builders;

import licenta_backend.controllers.exceptions.PlannerNotFound;
import licenta_backend.dtos.DetailsDTO;
import licenta_backend.dtos.NotificationDTO;
import licenta_backend.entities.DetailsEvent;
import licenta_backend.entities.Notification;
import licenta_backend.entities.Users;
import licenta_backend.repositories.OurWorkRepository;
import licenta_backend.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class NotificationBuilder {

    private static
    UserRepository userRepository;

    private NotificationBuilder( UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public static NotificationDTO tonotificationDTO(Notification notification) {
        return new NotificationDTO (notification.getId(), notification.getFromUser().getUsername(), notification.getToUser().getId(), notification.getMessage(), notification.getCreatedAt(),notification.getRead());
    }

    public static NotificationDTO tonotificationDTOFromUser(Notification notification) {
        return new NotificationDTO (notification.getId(), notification.getFromUser().getPlanner().getName(), notification.getToUser().getId(),userRepository.findUserByUsername(notification.getFromUser().getUsername()).get().getPlanner().getName(), notification.getMessage(), notification.getCreatedAt(),notification.getRead());
    }

    public static Notification  toEntity(NotificationDTO notificationDTO) {
        return new Notification(
                notificationDTO.getId(),
                userRepository.findUserByUsername(notificationDTO.getFromUser()).get(),
                userRepository.findById(notificationDTO.getToUser()).get(),
                notificationDTO.getMessage(),
                notificationDTO.getCreatedAt(),
                notificationDTO.getRead());



    }
}
