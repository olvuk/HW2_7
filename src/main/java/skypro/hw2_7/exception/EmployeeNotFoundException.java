package skypro.hw2_7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends HttpStatusCodeException {
    public EmployeeNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
