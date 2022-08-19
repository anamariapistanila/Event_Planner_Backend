package licenta_backend.entities;

import licenta_backend.utils.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Entity(name = "Users")
@Data
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "username", nullable = false)
    private String username;



    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    private Client client;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    private Planner planner;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    private Admin admin;

    @OneToMany(mappedBy = "toUser", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Notification> receivedNotifications;
    @OneToMany(mappedBy = "fromUser", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Notification> sendNotifications;

    public Users() {

    }


    public void addClient(Client client){
        this.client = client;
        client.setUser(this);
    }

    public void addPlanner(Planner planner){
        this.planner = planner;
        planner.setUser(this);
    }

    public void addAdmin(Admin admin){
        this.admin = admin;
        admin.setUser(this);
    }


    public Users(int id, String email, String password, Role role,String username) {
        this.id=id;
        this.email=email;
        this.password=password;
        this.role=role;
        this.username=username;
    }
}