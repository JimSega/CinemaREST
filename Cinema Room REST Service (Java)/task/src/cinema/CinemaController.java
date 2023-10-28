package cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@RestController
public class CinemaController {

    @Autowired
    private Cinema cinema;

    @GetMapping("/seats")
    public CinemaDTO getCinema() {
        return cinema.getAvailableSeats();
    }
    @PostMapping("/purchase")
    public synchronized ResponseEntity<?> getSeats(@RequestBody SeatsDTO seatDTO) {
        if(cinema.getSeats().stream()
                .filter(f -> f.getColumn() == seatDTO.column() && f.getRow() == seatDTO.row()).count() != 1) {
            return new ResponseEntity<>(Map
                    .of("error", ErrorMessage.OUT_OF_BOUNDS.toString()), HttpStatus.BAD_REQUEST);
        } else {
            Seats seats = cinema.getSeats().stream()
                    .filter(f -> f.getColumn() == seatDTO.column() && f.getRow() == seatDTO.row()).toList().get(0);
            if(!seats.isFree()) {
                return new ResponseEntity<>(Map
                        .of("error", ErrorMessage.HAS_BEEN_PURCHASED.toString()), HttpStatus.BAD_REQUEST);
            } else {
                seats.setFree(false);
                UUID uuid = UUID.randomUUID();
                cinema.getMapUUID().put(uuid, seats);
                return ResponseEntity.ok().body(new Token(uuid, seats));
            }
        }
    }
    @PostMapping("/return")
    public Map<String, Seats> returnSeats(@RequestBody Token token) {
        if (cinema.getMapUUID().containsKey(token.token())) {
            cinema.getMapUUID().get(token.token()).setFree(true);
            return Map.of("ticket", cinema.getMapUUID().remove(token.token()));
        }
        throw new TokenNotFoundException("Wrong token!");
    }

    @GetMapping("/stats")
    public Map<String, Integer> getStat(@RequestParam(required = false) String password) {

        if(password.equals(cinema.passwordSecret)) {
            List<Integer> incomeList = cinema.getSeats().stream()
                    .filter(f -> !f.isFree()).map(Seats::getPrice).toList();
            int income = incomeList.stream().reduce(0, Integer::sum);
            int available = (int) cinema.getSeats().stream().filter(Seats::isFree).count();
            int purchased = incomeList.size();
            return Map.of("income", income, "available", available, "purchased", purchased);
        }

        throw new PasswordWrong("The password is wrong!");
    }

}
