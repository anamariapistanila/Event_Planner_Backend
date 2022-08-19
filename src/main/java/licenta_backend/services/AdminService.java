package licenta_backend.services;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.PlannerDTO;
import licenta_backend.dtos.builders.ClientBuilder;
import licenta_backend.dtos.builders.PlannerBuilder;
import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import licenta_backend.repositories.AdminRepository;
import licenta_backend.repositories.ClientRepository;
import licenta_backend.repositories.PlannerRepository;
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
    private final UserService userService;
    private final ClientService clientService;
    private final PlannerService plannerService;
    private final AdminRepository adminRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

    public AdminService(ClientRepository clientRepository, PlannerRepository plannerRepository, UserService userService, ClientService clientService, PlannerService plannerService, AdminRepository adminRepository) {
        this.clientRepository = clientRepository;
        this.plannerRepository = plannerRepository;
        this.userService = userService;
        this.clientService = clientService;
        this.plannerService = plannerService;
        this.adminRepository = adminRepository;
    }

    public List<ClientDTO> findClients() {

        List<Client> clientsList = clientRepository.findAll();
        return clientsList.stream()
                .map(ClientBuilder::toclientUpdateDTO)
                .collect(Collectors.toList());
    }

    public List<PlannerDTO> findPlanners() {
        List<Planner> plannersList = plannerRepository.findAll();
        return plannersList.stream()
                .map(PlannerBuilder::toplannerUpdateDTO)
                .collect(Collectors.toList());
    }

    public void deletePlanner(int id){
        plannerRepository.deleteById(id);


    }

    public void deleteClient(int id){
        clientRepository.deleteById(id);


    }

    public PlannerDTO updatePlanner(int id,PlannerDTO plannerDTO) {

        PlannerDTO dto = plannerService.findPlannerById(id);
        dto.setName(plannerDTO.getName());
        dto.setEmail(plannerDTO.getEmail());
        dto.setPhone(plannerDTO.getPhone());

        Planner e = PlannerBuilder.toEntity(dto);

        plannerRepository.save(e);
        return PlannerBuilder.toplannerDTO(e);
    }
}
