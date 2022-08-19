package licenta_backend.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlannerNotFound extends RuntimeException {
    public PlannerNotFound(Integer id) {
        super(String.format("Planner  with id '%d' does not exist.", id));
    }
}