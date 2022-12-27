package licenta_backend.controllers;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.PlannerDTO;
import licenta_backend.services.ClientService;
import licenta_backend.services.CreateYourEventService;
import licenta_backend.services.PlannerService;
import licenta_backend.services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/client")
public class ClientController {
    private final PlannerService plannerService;
    private final ClientService clientService;
    private final CreateYourEventService createYourEventService;
    private final UserService userService;


    public ClientController(PlannerService plannerService, ClientService clientService, CreateYourEventService createYourEventService, UserService userService) {
        this.plannerService = plannerService;
        this.clientService = clientService;
        this.createYourEventService = createYourEventService;
        this.userService = userService;
    }

    @GetMapping("/allPlanners")
    public ResponseEntity<List<PlannerDTO>> allPlanners() {
        List<PlannerDTO> plannerList = plannerService.findPlanners();


        return new ResponseEntity<>(plannerList, HttpStatus.OK);
    }

    @GetMapping("/allPlannersDetails")
    public ResponseEntity<List<PlannerDTO>> allPlannersDetails() {
        List<PlannerDTO> plannerList = plannerService.findPlannersDetails();


        return new ResponseEntity<>(plannerList, HttpStatus.OK);
    }
    @GetMapping("/allClientsDetails")
    public ResponseEntity<List<ClientDTO>> allClientsDetails() {
        List<ClientDTO> clientList = clientService.findClientDetails();


        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }


    @GetMapping("/plannerById")
    public ResponseEntity<PlannerDTO> getPlannerById() {
        int id_logged=userService.getIdLogged();
        PlannerDTO planner = plannerService.findPlannerById(id_logged);


        return new ResponseEntity<>(planner, HttpStatus.OK);
    }

    @GetMapping("/clientById")
    public ResponseEntity<ClientDTO> getClientById() {
        int id_logged=userService.getIdLogged();
        ClientDTO client = clientService.findClientById(id_logged);


        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/allEvents")
    public ResponseEntity<List<CreateYourEventDTO>> allEvents() {
        List<CreateYourEventDTO> eventsList = createYourEventService.findAllEventsByClient();

for(CreateYourEventDTO dto: eventsList){
    System.out.println(dto.getLocation());
    System.out.println(dto.getPeriod_of_time_for_event());
}
        return new ResponseEntity<>(eventsList, HttpStatus.OK);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<JSONObject> updateProfile(@RequestBody ClientDTO clientDTO){
        JSONObject jsonObject= new JSONObject();
        String s = "Updated";
        jsonObject.put("Update Client",s);
        clientService.updateClientProfile(clientDTO);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }


}
