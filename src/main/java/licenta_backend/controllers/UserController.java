package licenta_backend.controllers;

import licenta_backend.dtos.*;
import licenta_backend.services.UserService;
import licenta_backend.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    String name="";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> dtos = userService.findUser();
        for (UserDTO dto : dtos) {
            Link userLink = linkTo(methodOn(UserController.class)
                    .getUser(dto.getId())).withRel("userDetails");

            dto.add(userLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int userId) {
        UserDTO dto = userService.findUserById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/getRoleFor/{username}")
    public ResponseEntity<String> getRoleForUser(@PathVariable("username") String username) {

        if(userService.getRole(username)!=null) {
            String role = userService.getRole(username);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.NOT_FOUND );
    }
    @GetMapping(value = "/getUserByUsername/{username}")
    public ResponseEntity<Integer> getUserByUsername(@PathVariable("username") String username) {

        int id=userService.getUser(username);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(value = "/registerClient")
    public ResponseEntity<Status> registerClient(@Valid @RequestBody UserClientDTO userClientDTO) {
        Status status = userService.createAccountClient(userClientDTO);
        name=userClientDTO.getclientDTO().getName();
        if(status.equals(status.FAILURE)){
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        } else
            return new ResponseEntity<>(status, HttpStatus.CREATED);
    }
    @PostMapping(value = "/registerPlanner")
    public ResponseEntity<Status> registerPlanner(@Valid @RequestBody UserPlannerDTO userPlannerDTO) {
        Status status = userService.createAccountPlanner(userPlannerDTO);
if(status.equals(status.FAILURE)){
    return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
} else
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PostMapping(value = "/registerAdmin")
    public ResponseEntity<Status> registerAdmin(@Valid @RequestBody UserAdminDTO userAdminDTO) {
        Status status = userService.createAccountAdmin(userAdminDTO);
        if(status.equals(status.FAILURE)){
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        } else
            return new ResponseEntity<>(status, HttpStatus.CREATED);
    }


    @PutMapping(value = "/changePassword/{email}")
    public ResponseEntity<UserDTO> changePassword(@PathVariable("email") String email, @Valid @RequestBody LoginDataDTO data){
        UserDTO userDTO = userService.updatePassword(email, data);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}




