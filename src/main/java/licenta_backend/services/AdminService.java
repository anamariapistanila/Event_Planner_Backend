package licenta_backend.services;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.PlannerDTO;
import licenta_backend.dtos.builders.ClientBuilder;
import licenta_backend.dtos.builders.PlannerBuilder;
import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import licenta_backend.repositories.ClientRepository;
import licenta_backend.repositories.PlannerRepository;
import licenta_backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {
    private final ClientRepository clientRepository;
    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;
    private final PlannerService plannerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

    public AdminService(ClientRepository clientRepository, PlannerRepository plannerRepository, UserRepository userRepository, PlannerService plannerService) {
        this.clientRepository = clientRepository;
        this.plannerRepository = plannerRepository;
        this.userRepository = userRepository;
        this.plannerService = plannerService;

    }

    public List<ClientDTO> findClients() {

        List<Client> clientsList = clientRepository.findAll();
        return clientsList.stream()
                .map(ClientBuilder::toclientUpdateProfileDTO)
                .collect(Collectors.toList());
    }

    public List<PlannerDTO> findPlanners() {
        List<Planner> plannersList = plannerRepository.findAll();
        return plannersList.stream()
                .map(PlannerBuilder::toplannerUpdateAdminDTO)
                .collect(Collectors.toList());
    }

    public void deletePlanner(int id){
        System.out.println(id);
        plannerRepository.deleteById(id);
        userRepository.deleteById(id);

    }

    public void deleteClient(int id){
        clientRepository.deleteById(id);
        userRepository.deleteById(id);


    }

    public PlannerDTO updatePlanner(int id,PlannerDTO plannerDTO) {

        PlannerDTO dto = plannerService.findPlannerById(id);
        dto.setName(plannerDTO.getName());
        dto.setEmail(plannerDTO.getEmail());
        dto.setPhone(plannerDTO.getPhone());
        dto.setType_of_planner(plannerDTO.getType_of_planner());

        Planner e = PlannerBuilder.toEntityAdmin(dto);

        plannerRepository.save(e);
        return PlannerBuilder.toplannerUpdateAdminDTO(e);
    }
}
