package licenta_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="CreateYourEvent")
@Data

@NoArgsConstructor

public class CreateYourEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "id_client", nullable = false)
    private int id_client;


    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "number_of_persons", nullable = false)
    private int number_of_persons;

    @Column(name = "period_of_time_for_event", nullable = false)
    private String period_of_time_for_event;

    @Column(name = "date", nullable = false)
    private String date;

    @ManyToOne( optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_planner", nullable = false)
    private Planner createYourEvent;

    @Column(name = "name_of_client")
    private String name_of_client;


    public CreateYourEvent(int id, int id_client, String location, int number_of_persons, String period_of_time_for_event, String date,String name_of_client, Planner createYourEvent) {
        this.id = id;
        this.id_client = id_client;
        this.location = location;
        this.number_of_persons = number_of_persons;
        this.period_of_time_for_event = period_of_time_for_event;
        this.date = date;
        this.name_of_client = name_of_client;
        this.createYourEvent = createYourEvent;

    }


}
