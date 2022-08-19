package licenta_backend.services;

import licenta_backend.dtos.DetailsDTO;
import licenta_backend.dtos.builders.DetailsBuilder;
import licenta_backend.entities.DetailsEvent;
import licenta_backend.repositories.DetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DetailsEventService {
    private final DetailsRepository detailsRepository;
    private final OurWorkService ourWorkService;

    public DetailsEventService(DetailsRepository detailsRepository, OurWorkService ourWorkService) {
        this.detailsRepository = detailsRepository;
        this.ourWorkService = ourWorkService;
    }

    public List<DetailsDTO> findDetailsById(int id) {
        List<DetailsEvent> detailsList = detailsRepository.findAllDetailsById(id);
        return detailsList.stream()
                .map(DetailsBuilder::todetailsDTO)
                .collect(Collectors.toList());
    }

    public int addDetails(DetailsDTO detailsDTO) {
      int id_event=ourWorkService.get_IdWork();
      System.out.print("id transmis din add"+ id_event);
      detailsDTO.setId_event(id_event);
        DetailsEvent event= DetailsBuilder.toEntity(detailsDTO);
        detailsRepository.save(event);
        return event.getId();

    }
}
