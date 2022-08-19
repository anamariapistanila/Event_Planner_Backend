package licenta_backend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="Comments")
@Data

@NoArgsConstructor

public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "name_of_client")
    private String name_of_client;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "id_client", nullable = false)
    private int id_client;


    @ManyToOne( optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "id_event")
    private OurWorkAdd events;

    public Comments(int id, String name_of_client, String comment, String date,int id_client, OurWorkAdd events) {
        this.id = id;
        this.name_of_client = name_of_client;
        this.comment = comment;
        this.date = date;
        this.id_client=id_client;
        this.events = events;
    }



}
