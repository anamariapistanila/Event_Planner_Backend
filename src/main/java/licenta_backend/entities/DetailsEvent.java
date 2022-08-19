package licenta_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="DetailsEvent")
@Data

@NoArgsConstructor

public class DetailsEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "description", length = 100000)
    private String description;

    @Column(name = "services", length = 10000)
    private String services;


    @Column(name = "id_event")
    private int id_event;

    public DetailsEvent(int id, String description, String services, int id_event) {
        this.id = id;
        this.description = description;
        this.services = services;
        this.id_event = id_event;
    }
}
