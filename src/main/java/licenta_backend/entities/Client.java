package licenta_backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;



@Entity(name = "Client")

@NoArgsConstructor
@Data
public class Client  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name = "name")
    private String name;


    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email", nullable = false)
    private String email;


    @Pattern(regexp = "^[0-9]{10}$")
    @Column(name = "phone", nullable = false)
    private String phone;


    @Column(name = "address")
    private String address;

    @Column(name = "birthdate")
    private String birthdate;



    @OneToOne
    @MapsId
    private Users user;

    public Client(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Client(int id, String name,  String email, String phone,String address, String birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;


    }

    public Client(int id, String name,  String email, String phone,String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;


    }

}
