package licenta_backend.services;

import licenta_backend.dtos.*;
import licenta_backend.dtos.builders.ClientBuilder;
import licenta_backend.dtos.builders.Client_PlannerBuilder;
import licenta_backend.dtos.builders.CreateYourEventBuilder;
import licenta_backend.dtos.builders.PlannerBuilder;
import licenta_backend.entities.Client;
import licenta_backend.entities.Client_Planner;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.entities.Planner;
import licenta_backend.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlannerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlannerService.class);

    private final PlannerRepository plannerRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final CreateYourEventRepository createEventRepository;
    private final ClientPlannerRepository clientPlannerRepository;
    private final UserService userService;
    private final ClientService clientService;
    private final CreateYourEventService createService;
    int id_client=0;

    @Autowired
    public PlannerService(PlannerRepository plannerRepository, ClientRepository clientRepository, UserRepository userRepository, CreateYourEventRepository createEventRepository, ClientPlannerRepository clientPlannerRepository, UserService userService, ClientService clientService, CreateYourEventService createService){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.createEventRepository = createEventRepository;
        this.plannerRepository=plannerRepository;
        this.clientPlannerRepository = clientPlannerRepository;
        this.userService = userService;
        this.clientService = clientService;
        this.createService = createService;
    }
    public PlannerDTO findPlannerById(int id) {
        Optional<Planner> prosumerOptional = plannerRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Planner with id {} was not found in db", id);
            throw new ResourceNotFoundException(Planner.class.getSimpleName() + " with ID: " + id);
        }
        return PlannerBuilder.toplannerDTO(prosumerOptional.get());
    }

    public List<PlannerDTO> findPlanners() {
        List<Planner> plannersList = plannerRepository.findAll();
        return plannersList.stream()
                .map(PlannerBuilder::toplannerDTO)
                .collect(Collectors.toList());
    }

    public List<PlannerDTO> findPlannersDetails() {
        List<Planner> plannersList = plannerRepository.findAll();
        return plannersList.stream()
                .map(PlannerBuilder::toplannerUpdateDTO)
                .collect(Collectors.toList());
    }


    public CreateYourEventDTO updateEvent(int id,CreateYourEventDTO eventDTO) {
        System.out.println(eventDTO.getDate());
        id_client=eventDTO.getId_client();
        CreateYourEventDTO dto = createService.findEventById(id);
        dto.setName_of_client(eventDTO.getName_of_client());
        dto.setLocation(eventDTO.getLocation());
        dto.setNumber_of_persons(eventDTO.getNumber_of_persons());
        dto.setPeriod_of_time_for_event(eventDTO.getPeriod_of_time_for_event());
        dto.setDate(eventDTO.getDate());


        CreateYourEvent e =CreateYourEventBuilder.toEntity(dto);

        createEventRepository.save(e);
        return CreateYourEventBuilder.toeventDTO(e);
    }
    public ClientDTO updateClient(int id,ClientDTO clientDTO) {

       ClientDTO dto = clientService.findClientUpdateById(id);
        dto.setEmail(clientDTO.getEmail());
        dto.setPhone(clientDTO.getPhone());
        dto.setAddress(clientDTO.getAddress());
        dto.setBirthdate(clientDTO.getBirthdate());


        Client e = ClientBuilder.toEntityUpdateProfile(dto);

        clientRepository.save(e);
        return ClientBuilder.toclientUpdateProfileDTO(e);
    }


    public List<CreateYourEventDTO> findAllEvents() {
        int id_planner= userService.getIdLogged();
        List<CreateYourEvent> eventList = createEventRepository.findAllForDisEvent(id_planner);

        return eventList.stream()
                .map(CreateYourEventBuilder::toeventDTO)
                .collect(Collectors.toList());
    }

    public List<Client_PlannerDTO> findAllClients() {
        int id_planner= userService.getIdLogged();
        List<Client_Planner> clientPlannerList = clientPlannerRepository.findAllClientsForPlanner(id_planner);
        return clientPlannerList.stream()
                .map(Client_PlannerBuilder::toclientplannerDTO)
                .collect(Collectors.toList());

    }

    public ClientDTO findClientUpdateById(int id) {
        Optional<Client> prosumerOptional = clientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Client with id {} was not found in db", id);
            throw new ResourceNotFoundException(Planner.class.getSimpleName() + " with ID: " + id);
        }
        return ClientBuilder.toclientUpdateProfileDTO(prosumerOptional.get());
    }
    public void deleteEvent(int id){
        createEventRepository.deleteByIdEvent(id);



    }

    public void deleteClient(int id) {
        int id_planner = userService.getIdLogged();

                clientPlannerRepository.deleteByIdClient(id,id_planner);
        userRepository.deleteById(id);


    }

    public PlannerDTO updatePlannerProfile( PlannerDTO profileDTO) {
        int id_planner = userService.getIdLogged();

        PlannerDTO dto = findPlannerById(id_planner);

        dto.setName(profileDTO.getName());
        dto.setEmail(profileDTO.getEmail());
        dto.setPhone(profileDTO.getPhone());
        dto.setType_of_planner(profileDTO.getType_of_planner());
        dto.setDescription(profileDTO.getDescription());
        dto.setImage_path(profileDTO.getImage_path());

        Planner planner = PlannerBuilder.toEntityUpdate(dto);

        plannerRepository.save(planner);
        PlannerBuilder pl=new PlannerBuilder();

        return pl.toplannerUpdateDTO(planner);
    }

    public int addEventPlanner(CreateYourEventDTO eventDTO) {
        int id_logged=userService.getIdLogged();
        eventDTO.setId_planner(id_logged);
        CreateYourEvent event= CreateYourEventBuilder.toEntity(eventDTO);
        createEventRepository.save(event);
        return event.getId();

    }


}
