package licenta_backend.dtos.builders;


import licenta_backend.dtos.UserDTO;
import licenta_backend.entities.Users;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserBuilder {

    public UserBuilder() {
    }


    public static UserDTO toUserDTO(Users user) {
        return new UserDTO(user.getId(),user.getEmail(), user.getPassword(), user.getRole(),user.getUsername());
    }

    public static Users toEntity(UserDTO userDTO) {
        return new Users(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole(),
                userDTO.getUsername());
    }
}
