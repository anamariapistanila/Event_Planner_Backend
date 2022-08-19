package licenta_backend.services;

import licenta_backend.dtos.CommentDTO;
import licenta_backend.dtos.OurWorkDTO;
import licenta_backend.dtos.builders.CommentBuilder;
import licenta_backend.dtos.builders.OurWorkBuilder;
import licenta_backend.entities.Comments;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.entities.OurWorkAdd;
import licenta_backend.repositories.OurWorkRepository;
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
public class OurWorkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OurWorkService.class);
    private final OurWorkRepository ourWorkRepository;
    private final UserService userService;
    private final PlannerService plannerService;
    int id_ourWork=0;


    public OurWorkService(OurWorkRepository ourWorkRepository, UserService userService, PlannerService plannerService) {
        this.ourWorkRepository = ourWorkRepository;
        this.userService = userService;
        this.plannerService = plannerService;
    }

    public List<OurWorkDTO> findOurWork() {
        List<OurWorkAdd> eventsList = ourWorkRepository.findAll();
        return eventsList.stream()
                .map(OurWorkBuilder::toeventDTO)
                .collect(Collectors.toList());
    }

    public OurWorkDTO findOurWorkById(int id) {
        Optional<OurWorkAdd> prosumerOptional = ourWorkRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Event with id {} was not found in db", id);
            throw new ResourceNotFoundException(CreateYourEvent.class.getSimpleName() + " with ID: " + id);
        }
        return OurWorkBuilder.toeventDTO(prosumerOptional.get());
    }

    public int get_IdWork()
    {
        return id_ourWork;
    }
    public int addYourEvent(OurWorkDTO eventDTO) {
        int id_planner= userService.getIdLogged();
        eventDTO.setPlanner_name(plannerService.findPlannerById(id_planner).getName());;
        OurWorkAdd event= OurWorkBuilder.toEntity(eventDTO);
        ourWorkRepository.save(event);
        id_ourWork=event.getId();
        System.out.println("id our work add"+ id_ourWork);
        return event.getId();

    }
}
