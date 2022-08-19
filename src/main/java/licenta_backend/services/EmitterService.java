package licenta_backend.services;
import licenta_backend.dtos.NotificationDTO;
import licenta_backend.dtos.builders.NotificationBuilder;
import licenta_backend.entities.Notification;
import licenta_backend.repositories.NotificationRepository;
import licenta_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmitterService {
    public final NotificationRepository notificationRepository;
    public final UserRepository userRepository;
    List<SseEmitter> emitters = new ArrayList<>();

    public void addEmitter(SseEmitter emitter) {
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitters.add(emitter);
    }

    public void pushNotification(NotificationDTO notificationDTO) {
        List<SseEmitter> deadEmitters = new ArrayList<>();

        Notification payload = NotificationBuilder.toEntity(notificationDTO);
        notificationRepository.save(payload);
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter
                        .event()
                        .name(notificationDTO.getFromUser())
                        .data(payload));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });

        emitters.removeAll(deadEmitters);
    }

}
