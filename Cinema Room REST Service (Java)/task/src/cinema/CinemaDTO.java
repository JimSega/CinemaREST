package cinema;

import java.util.List;

public record CinemaDTO(int rows, int columns, List<Seats> seats) {
}
