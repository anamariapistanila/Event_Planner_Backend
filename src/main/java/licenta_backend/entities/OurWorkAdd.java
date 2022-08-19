package licenta_backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="OurWorkAdd")
@Data

@NoArgsConstructor

public class OurWorkAdd implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "name_of_event", nullable = false)
    private String name_of_event;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "planner_name")
    private String planner_name;

    @Column(name = "image_path", nullable = false)
    private String image_path;

    @ManyToOne( optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_planner", nullable = false)
    private Planner event_planner;

    @OneToMany(mappedBy = "events", cascade=CascadeType.MERGE)
    private List<Comments> comments;





    public OurWorkAdd(int id, String name_of_event, String location, String planner_name, String image_path, Planner event_planner) {
        this.id = id;
        this.name_of_event = name_of_event;
        this.location = location;
        this.planner_name = planner_name;
        this.image_path = image_path;
        this.event_planner = event_planner;
    }
}
