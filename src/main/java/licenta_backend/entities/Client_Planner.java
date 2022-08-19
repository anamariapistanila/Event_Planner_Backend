package licenta_backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Client_Planner")

@NoArgsConstructor
@Data

public class Client_Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "id_client")
    private int id_client;


    @Column(name = "id_planner")
    private int id_planner;


    public Client_Planner(int id, int id_client, int id_planner) {
        this.id=id;
        this.id_client=id_client;
        this.id_planner=id_planner;
    }
}
