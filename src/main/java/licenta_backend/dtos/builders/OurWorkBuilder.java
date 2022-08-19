package licenta_backend.dtos.builders;

import licenta_backend.controllers.exceptions.PlannerNotFound;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.OurWorkDTO;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.entities.OurWorkAdd;
import licenta_backend.repositories.PlannerRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class OurWorkBuilder {
    private static
    PlannerRepository plannerRepository;

    private OurWorkBuilder(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }


    public static OurWorkDTO toeventDTO(OurWorkAdd event) {
        return new OurWorkDTO(event.getId(), event.getName_of_event(),event.getLocation(),event.getPlanner_name(),event.getImage_path(),event.getEvent_planner().getId());
    }

    public static OurWorkAdd toEntity(OurWorkDTO eventDTO) {
        return new OurWorkAdd(
                eventDTO.getId(),
                eventDTO.getName_of_event(),
                eventDTO.getLocation(),
                eventDTO.getPlanner_name(),
                eventDTO.getImage_path(),
                plannerRepository.findById(eventDTO.getId_planner()).orElseThrow(() -> new PlannerNotFound(eventDTO.getId_planner()))
        );


    }
}
