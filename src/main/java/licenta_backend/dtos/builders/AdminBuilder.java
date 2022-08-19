package licenta_backend.dtos.builders;

import licenta_backend.dtos.AdminDTO;
import licenta_backend.dtos.UserDTO;
import licenta_backend.entities.Admin;
import licenta_backend.entities.Users;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AdminBuilder {
    public AdminBuilder() {
    }


    public static AdminDTO toAdminDTO(Admin admin) {
        return new AdminDTO(admin.getId(),admin.getName(),admin.getEmail(), admin.getPhone());
    }

    public static Admin toEntity(AdminDTO adminDTO) {
        return new Admin(
                adminDTO.getId(),
                adminDTO.getName(),
                adminDTO.getEmail(),
                adminDTO.getPhone());

    }
}
