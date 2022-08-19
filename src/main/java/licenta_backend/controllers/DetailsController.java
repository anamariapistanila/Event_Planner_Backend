package licenta_backend.controllers;

import licenta_backend.dtos.CommentDTO;
import licenta_backend.dtos.DetailsDTO;
import licenta_backend.dtos.OurWorkDTO;
import licenta_backend.services.DetailsEventService;
import licenta_backend.services.OurWorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detailsEvent")
public class DetailsController {
    private final DetailsEventService detailsService;

    public DetailsController (DetailsEventService detailsService) {
        this.detailsService=detailsService;
    }

    @PostMapping("/addDetailsEvent")
    public ResponseEntity<Integer> addDetailsEvent(@RequestBody DetailsDTO detailsDTO) throws Exception{
        Integer id=detailsService.addDetails(detailsDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/getAllDetails/{id}")
    public ResponseEntity<List<DetailsDTO>> getAllDetails(@PathVariable int id) {
        System.out.println(id);
        List<DetailsDTO> detailsList = detailsService.findDetailsById(id);

        return new ResponseEntity<>(detailsList, HttpStatus.OK);
    }

}
