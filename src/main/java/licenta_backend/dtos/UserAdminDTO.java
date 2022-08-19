package licenta_backend.dtos;

public class UserAdminDTO {
    private UserDTO userDTO;
    private AdminDTO adminDTO;

    public UserAdminDTO() {
    }

    public UserAdminDTO(UserDTO userDTO, AdminDTO adminDTO) {
        this.userDTO = userDTO;
        this.adminDTO = adminDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public AdminDTO getAdminDTO() {
        return adminDTO;
    }

    public void setAdminDTO(AdminDTO adminDTO) {
        this.adminDTO = adminDTO;
    }
}
