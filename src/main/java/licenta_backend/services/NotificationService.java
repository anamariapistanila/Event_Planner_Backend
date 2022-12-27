package licenta_backend.services;


import licenta_backend.dtos.NotificationDTO;
import licenta_backend.dtos.builders.NotificationBuilder;
import licenta_backend.entities.Notification;
import licenta_backend.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class NotificationService {
    public final NotificationRepository notificationRepository;


    public List<NotificationDTO> getAllNotificationsByToUser(int userId) {
        return notificationRepository.findAllNotificationsByToUser(userId).stream()
                .map(NotificationBuilder::tonotificationDTOFromUser)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<NotificationDTO> update(Integer userId) {
        List<Notification> notifications = notificationRepository.findAllNotificationsByToUser(userId);
        for(Notification not: notifications){
            not.setRead(true);
        }
        List<NotificationDTO> dtos = notifications.stream()
                .map(NotificationBuilder::tonotificationDTO)
                .collect(Collectors.toList());
        return dtos;
    }


}