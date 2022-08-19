package licenta_backend.controllers;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.Client_PlannerDTO;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planner")
public class PlannerController {
    private final PlannerService plannerService;
    private final UserService userService;
    private final CreateYourEventService eventService;
    private final ClientService clientService;


    public PlannerController(PlannerService plannerService, UserService userService, CreateYourEventService eventService, ClientService clientService) {
        this.plannerService = plannerService;
        this.userService = userService;
        this.eventService = eventService;
        this.clientService = clientService;
    }

    @PutMapping("/updateEvent/{id}")
    public ResponseEntity<JSONObject> update(@PathVariable("id") int id, @RequestBody CreateYourEventDTO eventDTO){
        JSONObject jsonObject= new JSONObject();
        String s = "Updated";
        jsonObject.put("Update Event",s);
        plannerService.updateEvent(id, eventDTO);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<JSONObject> update(@PathVariable("id") int id, @RequestBody ClientDTO clientDTO){
        JSONObject jsonObject= new JSONObject();
        String s = "Updated";
        jsonObject.put("Update Client",s);
        plannerService.updateClient(id, clientDTO);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<JSONObject> updateProfile( @RequestBody PlannerDTO plannerDTO){
        JSONObject jsonObject= new JSONObject();
        String s = "Updated";
        jsonObject.put("Update Planner",s);
        plannerService.updatePlannerProfile(plannerDTO);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/addEventByPlanner")
    public ResponseEntity<Integer> addEventByPlanner(@RequestBody CreateYourEventDTO eventDTO) throws Exception{
        int id_planner=eventDTO.getId_planner();

        List<CreateYourEventDTO> events= eventService.findAllEvents(id_planner);

        int id =  plannerService.addEventPlanner(eventDTO);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @GetMapping("/plannerById")
    public ResponseEntity<PlannerDTO> getPlannerById() {
        int id_logged=userService.getIdLogged();
        PlannerDTO planner = plannerService.findPlannerById(id_logged);


        return new ResponseEntity<>(planner, HttpStatus.OK);
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<CreateYourEventDTO>>getAllEvents() {
        List<CreateYourEventDTO> eventList = plannerService.findAllEvents();

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @GetMapping("/getAllClients")
    public ResponseEntity<List<ClientDTO>>getAllClients() {


        List<Client_PlannerDTO> clientList = plannerService.findAllClients();
        List<ClientDTO> finalClients = new ArrayList<>();
        ClientDTO client = new ClientDTO();
        for (Client_PlannerDTO finalCl : clientList){
            client = plannerService.findClientUpdateById(finalCl.getId_client());
        System.out.print(client.getEmail());
        finalClients.add(client);
    }
        return new ResponseEntity<>(finalClients, HttpStatus.OK);
    }



    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<JSONObject> deleteEvent(@PathVariable("id") int id){
        JSONObject jsonObject= new JSONObject();
        plannerService.deleteEvent(id);
        String s = "Deleted";
        jsonObject.put("Delete Event",s);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<JSONObject> deleteClient(@PathVariable("id") int id){
        System.out.println(id);
        JSONObject jsonObject= new JSONObject();
        plannerService.deleteClient(id);
        String s = "Deleted";
        System.out.println("here");
        jsonObject.put("Delete Client",s);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }



}
