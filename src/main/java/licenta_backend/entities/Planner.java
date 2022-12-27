package licenta_backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Text;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
@Data
@Entity(name="Planner")

public class Planner implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "name")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{10}$")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "type_of_planner")
    private String type_of_planner;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "image_path")
    private String image_path;


    @OneToMany(mappedBy = "createYourEvent", fetch = FetchType.LAZY)
    private List<CreateYourEvent> events;

    @OneToMany(mappedBy = "event_planner", fetch = FetchType.LAZY)
    private List<OurWorkAdd> ourWork;

    @OneToOne
    @MapsId
    private Users user;


    public Planner(){}

    public Planner(int id, String name, String email, String phone) {
    this.id=id;
    this.name=name;
    this.email=email;
    this.phone=phone;
    }

    public Planner(int id, String name, String email, String phone, String type_of_planner, String description, String image_path) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type_of_planner = type_of_planner;
        this.description = description;
        this.image_path = image_path;
    }

    public Planner(int id, String name, String email, String phone, String type_of_planner) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type_of_planner = type_of_planner;
    }
}
