package licenta_backend.controllers;


import licenta_backend.dtos.NotificationDTO;
import licenta_backend.services.EmitterService;
import licenta_backend.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private EmitterService emitterService;
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/subscription")
    public SseEmitter subsribe() {
        SseEmitter sseEmitter = new SseEmitter(24 * 60 * 60 * 1000l);
        emitterService.addEmitter(sseEmitter);
        return sseEmitter;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Collection<NotificationDTO>> getNotificationsByToUser(@PathVariable Integer id) {
        List<NotificationDTO> dtos = notificationService.getAllByToUser(id);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> send(@PathVariable(required = false) Integer id, @RequestBody NotificationDTO request) {
        if (id==null) {
            request.setToUser(null);
        }
        else {
            request.setToUser(id);
        }
        request.setRead(false);
        request.setCreatedAt(LocalDateTime.now());
        emitterService.pushNotification(request);
        System.out.println(request);
        return ResponseEntity.ok().body(request);
    }




}