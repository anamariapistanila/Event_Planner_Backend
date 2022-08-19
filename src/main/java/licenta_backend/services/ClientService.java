package licenta_backend.services;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.PlannerDTO;
import licenta_backend.dtos.builders.ClientBuilder;
import licenta_backend.dtos.builders.PlannerBuilder;
import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import licenta_backend.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);


    public ClientService(ClientRepository clientRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }



    public List<ClientDTO> findClientDetails() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map(ClientBuilder::toclientUpdateDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findClientById(int id) {
        Optional<Client> prosumerOptional = clientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", id);
            throw new ResourceNotFoundException(Planner.class.getSimpleName() + " with ID: " + id);
        }
        return ClientBuilder.toclientDTO(prosumerOptional.get());
    }
    public ClientDTO findClientUpdateById(int id) {
        Optional<Client> prosumerOptional = clientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", id);
            throw new ResourceNotFoundException(Planner.class.getSimpleName() + " with ID: " + id);
        }
        return ClientBuilder.toclientUpdateDTO(prosumerOptional.get());
    }


    public ClientDTO updateClientProfile( ClientDTO profileDTO) {
        int id_planner = userService.getIdLogged();

        ClientDTO dto = findClientById(id_planner);

        dto.setName(profileDTO.getName());
        dto.setEmail(profileDTO.getEmail());
        dto.setPhone(profileDTO.getPhone());
        dto.setAddress(profileDTO.getAddress());
        dto.setBirthdate(profileDTO.getBirthdate());

        Client client = ClientBuilder.toEntityUpdateProfile(dto);

        clientRepository.save(client);


        return ClientBuilder.toclientUpdateProfileDTO(client);
    }

}
