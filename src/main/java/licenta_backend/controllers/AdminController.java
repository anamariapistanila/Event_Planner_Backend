package licenta_backend.controllers;

import licenta_backend.dtos.ClientDTO;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.OurWorkDTO;
import licenta_backend.dtos.PlannerDTO;
import licenta_backend.services.AdminService;
import licenta_backend.services.OurWorkService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final OurWorkService ourWorkService;


    public AdminController(AdminService adminService, OurWorkService ourWorkService) {
        this.adminService = adminService;

        this.ourWorkService = ourWorkService;
    }

    @GetMapping("/allPlanners")
    public ResponseEntity<List<PlannerDTO>> allPlanners() {
        List<PlannerDTO> plannerList = adminService.findPlanners();


        return new ResponseEntity<>(plannerList, HttpStatus.OK);
    }

    @GetMapping("/allClients")
    public ResponseEntity<List<ClientDTO>> allClients() {
        List<ClientDTO> clientList = adminService.findClients();


        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @DeleteMapping("/deletePlanner/{id}")
    public ResponseEntity<JSONObject> deletePlanner(@PathVariable("id") int id){
        JSONObject jsonObject= new JSONObject();
        adminService.deletePlanner(id);
        String s = "Deleted";
        jsonObject.put("Delete Planner",s);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<JSONObject> deleteClient(@PathVariable("id") int id){
        JSONObject jsonObject= new JSONObject();
        adminService.deleteClient(id);
        String s = "Deleted";
        jsonObject.put("Delete Client",s);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PutMapping("/updatePlanner/{id}")
    public ResponseEntity<JSONObject> update(@PathVariable("id") int id, @RequestBody PlannerDTO plannerDTO){
        JSONObject jsonObject= new JSONObject();
        String s = "Updated";
        jsonObject.put("Update Planner",s);
        adminService.updatePlanner(id, plannerDTO);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    @GetMapping("/allEvents")
    public ResponseEntity<List<OurWorkDTO>> allEventsOurWork() {
        List<OurWorkDTO> eventsList = ourWorkService.findOurWork();

        for(OurWorkDTO dto: eventsList){
            System.out.println(dto.getLocation());
            System.out.println(dto.getPlanner_name());
        }
        return new ResponseEntity<>(eventsList, HttpStatus.OK);
    }

}
