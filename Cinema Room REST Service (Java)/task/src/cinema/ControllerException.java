package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<Map<String, String>> tokenNotFound() {
        return ResponseEntity.badRequest().body(Map.of("error", "Wrong token!"));
    }

    @ExceptionHandler({PasswordWrong.class, NullPointerException.class})
    public ResponseEntity<Map<String, String>> passwordWrong() {
        return ResponseEntity.status(401).body(Map.of("error", "The password is wrong!"));
    }

}
