package licenta_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@NoArgsConstructor
@Data
public class NotificationDTO {
    private int id;
    private String fromUser;
    private Integer toUser;
    private String fromUserName;
    private String message;
    private LocalDateTime createdAt;
    private Boolean read;

    public NotificationDTO(int id, String fromUser, Integer toUser, String message, LocalDateTime createdAt, Boolean read) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
        this.createdAt = createdAt;
        this.read=read;
    }

    public NotificationDTO(int id, String fromUser, Integer toUser, String fromUserName, String message, LocalDateTime createdAt, Boolean read) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.fromUserName = fromUserName;
        this.message = message;
        this.createdAt = createdAt;
        this.read=read;
    }
}
