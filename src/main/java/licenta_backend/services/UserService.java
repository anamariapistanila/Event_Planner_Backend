package licenta_backend.services;

import licenta_backend.dtos.*;
import licenta_backend.dtos.builders.AdminBuilder;
import licenta_backend.dtos.builders.ClientBuilder;
import licenta_backend.dtos.builders.PlannerBuilder;
import licenta_backend.dtos.builders.UserBuilder;
import licenta_backend.entities.Admin;
import licenta_backend.entities.Client;
import licenta_backend.entities.Planner;
import licenta_backend.entities.Users;
import licenta_backend.repositories.AdminRepository;
import licenta_backend.repositories.ClientRepository;
import licenta_backend.repositories.PlannerRepository;
import licenta_backend.repositories.UserRepository;
import licenta_backend.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PlannerRepository plannerRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    int id_logged=0;
    String name="";

    @Autowired
    public UserService(UserRepository userRepository, ClientRepository clientRepository, PlannerRepository plannerRepository, AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.plannerRepository = plannerRepository;
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Status verifyUser(Users user) {

        List<Users> users = userRepository.findAll();
        for (Users u : users) {
            System.out.println(u.getUsername());
            System.out.println(u.equals(user));
            if (u.getUsername().equals(user.getUsername()) && u.getRole().equals(user.getRole())) {
                LOGGER.info("User Already exists!");
                System.out.println("User already exists");

                return Status.USER_ALREADY_EXISTS;
            }
        }
        return Status.USER_DOSNT_EXISTS;
    }
    public List<UserDTO> findUser() {
        List<Users> UserList = userRepository.findAll();
        return UserList.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(int id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserDTO(userOptional.get());
    }
    public String getRole(String username) {
        Users user = userRepository.findByUsername(username);

        return user.getRole().toString();
    }
    public int getUser(String username) {
        Users user = userRepository.findByUsername(username);
        id_logged=user.getId();
       // System.out.println(planner_id_logged);
        return user.getId();
    }

    public String getUserName(String username) {
        Users user = userRepository.findByUsername(username);

         System.out.println( user.getUsername());
        return user.getUsername();
    }
    public int getIdLogged(){

        return id_logged;
    }

    public String getNameClient(){

       return name;
    }

    public List<Integer> findAllDetailsForUsers() {
        List<Users> userList = userRepository.findAll();
        List<Integer> idList = new ArrayList<>();
        for(Users u: userList){
            idList.add(u.getId());
        }
        return idList;
    }
    public Status createAccountClient(UserClientDTO userClientDTO) {
        Users user = UserBuilder.toEntity(userClientDTO.getUserDTO());
        if(verifyUser(user).equals(Status.USER_DOSNT_EXISTS)) {
            System.out.println(verifyUser(user).equals(Status.USER_DOSNT_EXISTS));
            user.setPassword(bCryptPasswordEncoder.encode(userClientDTO.getUserDTO().getPassword()));
            Client client = ClientBuilder.toEntity(userClientDTO.getclientDTO());

            LOGGER.debug("Client with id {} - created!", client.getId());
            user.addClient(client);
            clientRepository.save(client);
             name=client.getName();

            user = userRepository.save(user);
            return Status.CLIENT_ADDED_SUCCESSFULLY;
        }
        return Status.FAILURE;
    }
    public Status createAccountPlanner(UserPlannerDTO userPlannerDTO) {
        Users user = UserBuilder.toEntity(userPlannerDTO.getUserDTO());
        if(verifyUser(user).equals(Status.USER_DOSNT_EXISTS)) {
            System.out.println(verifyUser(user).equals(Status.USER_DOSNT_EXISTS));
            user.setPassword(bCryptPasswordEncoder.encode(userPlannerDTO.getUserDTO().getPassword()));
            Planner planner = PlannerBuilder.toEntity(userPlannerDTO.getPlannerDTO());

            LOGGER.debug("Planner with id {} - created!", planner.getId());
            user.addPlanner(planner);


            plannerRepository.save(planner);

            user = userRepository.save(user);
            return Status.PLANNER_ADDED_SUCCESSFULLY;
        }
        else return Status.FAILURE;
    }

    public Status createAccountAdmin(UserAdminDTO userAdminDTO) {
        Users user = UserBuilder.toEntity(userAdminDTO.getUserDTO());
        if(verifyUser(user).equals(Status.USER_DOSNT_EXISTS)) {
            user.setPassword(bCryptPasswordEncoder.encode(userAdminDTO.getUserDTO().getPassword()));
            Admin admin = AdminBuilder.toEntity(userAdminDTO.getAdminDTO());

            LOGGER.debug("Admin with id {} - created!", admin.getId());
            user.addAdmin(admin);

            adminRepository.save(admin);
            name=admin.getName();

            user = userRepository.save(user);
            return Status.ADMIN_ADDED_SUCCESSFULLY;
        }
        return Status.FAILURE;
    }
    public UserDTO updatePassword(String email, LoginDataDTO data){
        UserDTO userDTO = null;
        if(userRepository.findByEmail(email) != null) {
            Users user = userRepository.findByEmail(email);

            if ((user.getUsername()).equals(data.getUsername())) {
                System.out.println(true);
                user.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
                userRepository.save(user);
            }
            userDTO = UserBuilder.toUserDTO(user);
        }
        return userDTO;
    }



}