package licenta_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@Data

@NoArgsConstructor
public class OurWorkDTO extends RepresentationModel<OurWorkDTO> {
    private int id;
    private String name_of_event;
    private String location;
    private String planner_name;
    private String image_path;
    private int id_planner;

    public OurWorkDTO(int id, String name_of_event, String location, String planner_name,String image_path,int id_planner) {
        this.id = id;
        this.name_of_event = name_of_event;
        this.location = location;
        this.planner_name = planner_name;
        this.image_path=image_path;
        this.id_planner = id_planner;
    }
}
