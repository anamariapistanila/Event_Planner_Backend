package licenta_backend.dtos;

import licenta_backend.entities.OurWorkAdd;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
@Data

@NoArgsConstructor
public class DetailsDTO extends RepresentationModel<DetailsDTO> {

    private int id;
    private String description;
    private String services;
    private int id_event;

    public DetailsDTO(int id, String description, String services, int id_event) {
        this.id = id;
        this.description = description;
        this.services = services;
        this.id_event = id_event;
    }
}
