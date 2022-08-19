package licenta_backend.controllers;

import licenta_backend.dtos.Client_PlannerDTO;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.services.Client_PlannerService;
import licenta_backend.services.CreateYourEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/createEvent")
public class CreateYourEventController {
    private final CreateYourEventService eventService;
    private final Client_PlannerService client_plannerService;


    public CreateYourEventController(CreateYourEventService eventService, Client_PlannerService client_plannerService) {
        this.eventService = eventService;
        this.client_plannerService = client_plannerService;
    }

    @PostMapping("/addClientEvent")
    public ResponseEntity<Integer> addClientEvent(@RequestBody CreateYourEventDTO eventDTO) throws Exception{
          System.out.println(eventDTO.getDate());
        int id_planner=eventDTO.getId_planner();

        List<CreateYourEventDTO> events= eventService.findAllEvents(id_planner);

        int id =  eventService.addYourEvent(eventDTO);
        System.out.println(eventDTO.getLocation());
        System.out.println(eventDTO.getDate());
        System.out.println(eventDTO.getNumber_of_persons());
        System.out.println(eventDTO.getPeriod_of_time_for_event());

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/addClientPlanner")
    public ResponseEntity<Integer> addClientPlanner(@RequestBody Client_PlannerDTO client_plannerDTO) throws Exception{

        int id=client_plannerService.addClientPlanner(client_plannerDTO);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
