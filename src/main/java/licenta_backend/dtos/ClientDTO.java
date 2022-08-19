package licenta_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data

@NoArgsConstructor

public class ClientDTO extends RepresentationModel<ClientDTO> {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String birthdate;




    public ClientDTO(int id, String name, String email, String phone) {
        this.id = id;
        this.name=name;
        this.email = email;
        this.phone = phone;

    }


    public ClientDTO(int id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public ClientDTO(int id, String name, String email, String phone, String address, String birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
    }
}
