package licenta_backend.dtos.builders;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.Client_PlannerDTO;
import licenta_backend.entities.Client;
import licenta_backend.entities.Client_Planner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class Client_PlannerBuilder {
    public static Client_PlannerDTO toclientplannerDTO(Client_Planner clientplanner) {
        return new Client_PlannerDTO(clientplanner.getId(),clientplanner.getId_client(),clientplanner.getId_planner());
    }


    public static Client_Planner toEntity(Client_PlannerDTO clientplannerDTO) {
        return new Client_Planner(
                clientplannerDTO.getId(),
                clientplannerDTO.getId_client(),
                clientplannerDTO.getId_planner());
    }
}
