package licenta_backend.dtos;

public class UserClientDTO {
    private UserDTO userDTO;
    private ClientDTO clientDTO;

    public UserClientDTO() {
    }

    public UserClientDTO(UserDTO userDTO, ClientDTO clientDTO) {
        this.userDTO = userDTO;
        this.clientDTO = clientDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ClientDTO getclientDTO() {
        return clientDTO;
    }

    public void setClientTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}
