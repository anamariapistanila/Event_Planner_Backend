package licenta_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data

@NoArgsConstructor
public class CreateYourEventDTO  extends RepresentationModel<CreateYourEventDTO> {
    private int id;
    private int id_client;
    private String location;
    private int number_of_persons;
    private String period_of_time_for_event;
    private String date;
    private String name_of_client;
    private int id_planner;

    public CreateYourEventDTO(int id, int id_client, String location, int number_of_persons, String period_of_time, String date,String name,int id_planner) {
        this.id = id;
        this.id_client = id_client;
        this.location = location;
        this.number_of_persons = number_of_persons;
        this.period_of_time_for_event = period_of_time;
        this.date = date;
        this.name_of_client=name;
        this.id_planner = id_planner;

    }


}
