package licenta_backend.services;

import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.builders.CreateYourEventBuilder;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.repositories.CreateYourEventRepository;
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
public class CreateYourEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateYourEventService.class);
    private final CreateYourEventRepository createEventRepository;
    private final UserService userService;
    private final ClientService clientService;

    public CreateYourEventService(CreateYourEventRepository createEventRepository, UserService userService, ClientService clientService) {
        this.createEventRepository = createEventRepository;
        this.userService = userService;
        this.clientService = clientService;
    }

    public CreateYourEventDTO findEventById(int id) {
        Optional<CreateYourEvent> prosumerOptional = createEventRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Event with id {} was not found in db", id);
            throw new ResourceNotFoundException(CreateYourEvent.class.getSimpleName() + " with ID: " + id);
        }
        return CreateYourEventBuilder.toeventDTO(prosumerOptional.get());
    }

    public List<CreateYourEventDTO> findAllEvents(int id_planner) {
        List<CreateYourEvent> eventList = createEventRepository.findAllForDisEvent(id_planner);
        return eventList.stream()
                .map(CreateYourEventBuilder::toeventDTO)
                .collect(Collectors.toList());
    }

    public int addYourEvent(CreateYourEventDTO eventDTO) {

        eventDTO.setName_of_client(clientService.findClientById(eventDTO.getId_client()).getName());
        System.out.println(clientService.findClientById(eventDTO.getId_client()).getName());
        CreateYourEvent event= CreateYourEventBuilder.toEntity(eventDTO);
        createEventRepository.save(event);
        return event.getId();

    }

    public List<CreateYourEventDTO> findAllEventsByClient() {
        int id_client= userService.getIdLogged();
        List<CreateYourEvent> eventList = createEventRepository.findAllEventsClient(id_client);
        return eventList.stream()
                .map(CreateYourEventBuilder::toeventDTO)
                .collect(Collectors.toList());
    }
}
