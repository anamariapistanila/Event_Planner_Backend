package licenta_backend.dtos;

import licenta_backend.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data

@NoArgsConstructor

public class UserDTO extends RepresentationModel<UserDTO> {
    private int id;
    private String email;
    private String password;
    private Role role;
    private String username;

    public UserDTO(int id,String email, String password, Role role,String username) {
        this.id = id;
        this.email=email;
        this.password = password;
        this.role = role;
        this.username = username;
    }


}
