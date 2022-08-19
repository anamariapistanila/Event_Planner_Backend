package licenta_backend.dtos.builders;

import licenta_backend.controllers.exceptions.PlannerNotFound;
import licenta_backend.dtos.CommentDTO;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.entities.Comments;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.repositories.CreateYourEventRepository;
import licenta_backend.repositories.OurWorkRepository;
import licenta_backend.repositories.PlannerRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CommentBuilder {

    private static
    OurWorkRepository ourWorkRepository;
    private CommentBuilder( OurWorkRepository ourWorkRepository) {
        this.ourWorkRepository=ourWorkRepository;
    }

    public static CommentDTO tocommentDTO(Comments comment) {
        return new CommentDTO(comment.getId(), comment.getName_of_client(), comment.getComment(), comment.getDate(),comment.getId_client(),comment.getEvents().getId());
    }

    public static Comments toEntity(CommentDTO commentDTO) {
        return new Comments(
                commentDTO.getId(),
                commentDTO.getName_of_client(),
                commentDTO.getComment(),
                commentDTO.getDate(),
                commentDTO.getId_client(),
                ourWorkRepository.findById(commentDTO.getId_event()).orElseThrow(() -> new PlannerNotFound(commentDTO.getId_event())));



    }
}
