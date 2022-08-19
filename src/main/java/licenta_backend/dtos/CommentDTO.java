package licenta_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@Data

@NoArgsConstructor
public class CommentDTO extends RepresentationModel<CommentDTO> {
   private int id;
   private String name_of_client;
    private String comment;
    private String date;
    private int id_client;
   private int id_event;

    public CommentDTO(int id, String name_of_client,String comment, String date,int id_client, int id_event) {
        this.id = id;
        this.name_of_client = name_of_client;
        this.comment=comment;
        this.date = date;
        this.id_client=id_client;
        this.id_event = id_event;
    }
}
