package licenta_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.awt.*;

@Data

@NoArgsConstructor
public class PlannerDTO extends RepresentationModel<licenta_backend.dtos.PlannerDTO> {
        private int id;
        private String name;
        private String email;
        private String phone;
    private String type_of_planner;
    private String description;
    private String image_path;


        public PlannerDTO(int id, String name, String email, String phone) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

    public PlannerDTO(int id, String name, String email, String phone, String type_of_planner, String description, String image_path) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type_of_planner = type_of_planner;
        this.description = description;
        this.image_path = image_path;
    }

    public PlannerDTO(int id, String name, String email, String phone, String type_of_planner) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type_of_planner = type_of_planner;
    }

    public int getId() {
        return id;
    }
}