package licenta_backend.services;

import licenta_backend.dtos.CommentDTO;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.dtos.OurWorkDTO;
import licenta_backend.dtos.builders.CommentBuilder;
import licenta_backend.dtos.builders.CreateYourEventBuilder;
import licenta_backend.entities.Comments;
import licenta_backend.entities.CreateYourEvent;
import licenta_backend.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final OurWorkService ourWorkService;
    private final ClientService clientService;
    private final CreateYourEventService createService;

    public CommentService(CommentRepository commentRepository, OurWorkService ourWorkService, ClientService clientService, CreateYourEventService createService) {
        this.commentRepository = commentRepository;
        this.ourWorkService = ourWorkService;
        this.clientService = clientService;
        this.createService = createService;
    }
    public CommentDTO findCommentById(int id) {
        Optional<Comments> prosumerOptional = commentRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Event with id {} was not found in db", id);
            throw new ResourceNotFoundException(CreateYourEvent.class.getSimpleName() + " with ID: " + id);
        }
        return CommentBuilder.tocommentDTO(prosumerOptional.get());
    }
    public List<CommentDTO> findCommentsByOurWorkId(int id_event) {
        List<Comments> commentsList = commentRepository.findAllCommentsById(id_event);
        return commentsList.stream()
                .map(CommentBuilder::tocommentDTO)
                .collect(Collectors.toList());
    }

    public int addYourComment(CommentDTO commentDTO) {

        commentDTO.setName_of_client(clientService.findClientById(commentDTO.getId_client()).getName());
        Comments comment= CommentBuilder.toEntity(commentDTO);
        commentRepository.save(comment);
        return comment.getId();

    }

}
