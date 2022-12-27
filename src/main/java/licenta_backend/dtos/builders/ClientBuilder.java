package licenta_backend.dtos.builders;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.entities.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ClientBuilder {

    public static ClientDTO toclientDTO(Client client) {
        return new ClientDTO(client.getId(),client.getName(),client.getEmail(),client.getPhone());
    }


    public static ClientDTO toclientUpdateDTO(Client client) {
        return new ClientDTO(client.getId(),client.getName(),client.getEmail(),client.getPhone(), client.getAddress());
    }
    public static ClientDTO toclientUpdateProfileDTO(Client client) {
        return new ClientDTO(client.getId(),client.getName(),client.getEmail(),client.getPhone(), client.getAddress(),client.getBirthdate());
    }

    public static Client toEntity(ClientDTO clientDTO) {
        return new Client(
                clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getEmail(),
                clientDTO.getPhone());
    }

    public static Client toEntityUpdateProfile(ClientDTO clientDTO) {
        return new Client(
                clientDTO.getId(),
                clientDTO.getName(),
                clientDTO.getEmail(),
                clientDTO.getPhone(),
                clientDTO.getAddress(),
                clientDTO.getBirthdate());
    }
}
