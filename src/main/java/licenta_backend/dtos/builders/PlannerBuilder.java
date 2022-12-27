package licenta_backend.dtos.builders;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.PlannerDTO;
import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PlannerBuilder {

    public PlannerBuilder(){}
    public static PlannerDTO toplannerDTO(Planner planner) {
        return new PlannerDTO(planner.getId(),planner.getName(), planner.getEmail(), planner.getPhone());
    }
    public static PlannerDTO toplannerUpdateDTO(Planner planner) {
        return new PlannerDTO(planner.getId(), planner.getName(), planner.getEmail(), planner.getPhone(),planner.getType_of_planner(), planner.getDescription(), planner.getImage_path());
    }

    public static PlannerDTO toplannerUpdateAdminDTO(Planner planner) {
        return new PlannerDTO(planner.getId(),planner.getName(), planner.getEmail(), planner.getPhone(), planner.getType_of_planner());
    }

    public static Planner toEntity(PlannerDTO plannerDTO) {
        return new Planner(
                plannerDTO.getId(),
                plannerDTO.getName(),
                plannerDTO.getEmail(),
                plannerDTO.getPhone());
    }

    public static Planner toEntityAdmin(PlannerDTO plannerDTO) {
        return new Planner(
                plannerDTO.getId(),
                plannerDTO.getName(),
                plannerDTO.getEmail(),
                plannerDTO.getPhone(),
                plannerDTO.getType_of_planner());
    }

    public static Planner toEntityUpdate(PlannerDTO plannerDTO) {
        return new Planner(
                plannerDTO.getId(),
                plannerDTO.getName(),
                plannerDTO.getEmail(),
                plannerDTO.getPhone(),
                plannerDTO.getType_of_planner(),
                plannerDTO.getDescription(),
                plannerDTO.getImage_path());
    }
}
