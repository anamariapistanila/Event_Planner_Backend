package licenta_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

public class AdminDTO {
    private int id;
    private String name;
    private String email;
    private String phone;

    public AdminDTO(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
