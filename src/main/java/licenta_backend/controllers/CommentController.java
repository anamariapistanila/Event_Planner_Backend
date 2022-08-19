package licenta_backend.controllers;

import licenta_backend.dtos.CommentDTO;
import licenta_backend.dtos.CreateYourEventDTO;
import licenta_backend.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comm")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAllComments/{id}")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable int id) {
        System.out.println(id);
        List<CommentDTO> commentList = commentService.findCommentsByOurWorkId(id);

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @PostMapping("/addCommentClient")
    public ResponseEntity<Integer> addCommentClient(@RequestBody CommentDTO commentDTO) throws Exception{

        Integer comment=commentService.addYourComment(commentDTO);



        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
