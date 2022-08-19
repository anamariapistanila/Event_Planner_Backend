package licenta_backend.dtos.builders;

import licenta_backend.controllers.exceptions.PlannerNotFound;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.DetailsDTO;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.entities.DetailsEvent;
import licenta_backend.repositories.OurWorkRepository;
import licenta_backend.repositories.PlannerRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class DetailsBuilder {

    private static
    OurWorkRepository  ourWorkRepository;

    private DetailsBuilder(OurWorkRepository  ourWorkRepository) {
       this.ourWorkRepository=ourWorkRepository;
    }

    public static DetailsDTO todetailsDTO(DetailsEvent details) {
        return new DetailsDTO(details.getId(), details.getDescription(), details.getServices(), details.getId_event());
    }

    public static DetailsEvent toEntity(DetailsDTO detailsDTO) {
        return new DetailsEvent(
             detailsDTO.getId(),
                detailsDTO.getDescription(),
                detailsDTO.getServices(),
                detailsDTO.getId_event());



    }
}
