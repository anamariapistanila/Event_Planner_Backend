package licenta_backend.services;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.Client_PlannerDTO;
import licenta_backend.dtos.builders.ClientBuilder;
import licenta_backend.dtos.builders.Client_PlannerBuilder;
import licenta_backend.entities.Client;
import licenta_backend.entities.Client_Planner;
import licenta_backend.repositories.ClientPlannerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class Client_PlannerService {
    private final ClientPlannerRepository clientPlannerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    public Client_PlannerService(ClientPlannerRepository clientPlannerRepository) {
        this.clientPlannerRepository = clientPlannerRepository;
    }


    public int addClientPlanner(Client_PlannerDTO client_plannerDTO) {
        Client_Planner client_planner= Client_PlannerBuilder.toEntity(client_plannerDTO);
        clientPlannerRepository.save(client_planner);
        return client_planner.getId();

    }
}
