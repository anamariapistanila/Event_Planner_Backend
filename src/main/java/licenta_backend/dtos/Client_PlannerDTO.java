package licenta_backend.dtos;

import licenta_backend.entities.Client_Planner;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data

@NoArgsConstructor
public class Client_PlannerDTO extends RepresentationModel<Client_PlannerDTO> {
    private int id;
    private int id_client;
    private int id_planner;


    public Client_PlannerDTO(int id, int id_client, int id_planner) {
        this.id = id;
        this.id_client = id_client;
        this.id_planner = id_planner;
    }
}
