package licenta_backend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;



@Entity(name = "Vendors")
@Data
@AllArgsConstructor

@NoArgsConstructor

public class Vendors implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "type_of_vendor")
    private String type_of_vendor;

    @ManyToOne( optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_planner", nullable = false)
    private Planner planner;

}