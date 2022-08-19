package licenta_backend.dtos.builders;

import licenta_backend.controllers.exceptions.PlannerNotFound;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.repositories.PlannerRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CreateYourEventBuilder {
    private static
    PlannerRepository plannerRepository;

    private CreateYourEventBuilder(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    public static CreateYourEventDTO toeventDTO(CreateYourEvent event) {
        return new CreateYourEventDTO(event.getId(), event.getId_client(), event.getLocation(), event.getNumber_of_persons(), event.getPeriod_of_time_for_event(), event.getDate(), event.getName_of_client(),event.getCreateYourEvent().getId());
    }

    public static CreateYourEvent toEntity(CreateYourEventDTO eventDTO) {
        return new CreateYourEvent(
                eventDTO.getId(),
                eventDTO.getId_client(),
                eventDTO.getLocation(),
                eventDTO.getNumber_of_persons(),
                eventDTO.getPeriod_of_time_for_event(),
                eventDTO.getDate(),
                eventDTO.getName_of_client(),
                plannerRepository.findById(eventDTO.getId_planner()).orElseThrow(() -> new PlannerNotFound(eventDTO.getId_planner())));



    }
}