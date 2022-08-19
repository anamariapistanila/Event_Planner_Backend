package licenta_backend.controllers;

import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.OurWorkDTO;
import licenta_backend.services.Client_PlannerService;
import licenta_backend.services.CreateYourEventService;
import licenta_backend.services.OurWorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ourWork")
public class OurWorkController {
    private final OurWorkService ourWorkService;

    public OurWorkController (OurWorkService ourWorkService) {
      this.ourWorkService=ourWorkService;
    }
    @PostMapping("/addOurWork")
    public ResponseEntity<Integer> addOurWork(@RequestBody OurWorkDTO eventDTO) throws Exception{
        Integer id=ourWorkService.addYourEvent(eventDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/getOurWork")
    public ResponseEntity<List<OurWorkDTO>>getOurWork() {
        List<OurWorkDTO> eventList = ourWorkService.findOurWork();

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }


}
